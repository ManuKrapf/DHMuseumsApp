package dh.computermuseum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;
import dh.computermuseum.Component.Tag;

/**
 * Start parsing xml-data with a content handler and stores the parsed data
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class Data {
	
	Context context; 
	
	private ArrayList<Computer> computers;
	private ArrayList<Component> components;
	private ArrayList<Storage> storages;
	private AntiquesContentHandler ach;
	
	/**
	 * Constructor for initialisation of the Data object
	 * 
	 * @param c the Context of the Activity that creates the object
	 */
	public Data(Context c) {
		context = c;
		computers = new ArrayList<Computer>();
		components = new ArrayList<Component>();
		storages = new ArrayList<Storage>();
		initXMLParser();
	}
	
	/**
	 * starts parsing the xml-file with the content handler
	 */
	private void initXMLParser() {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser newSAXParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = newSAXParser.getXMLReader();
			// read XML input file here:
			BufferedReader br = new BufferedReader(new InputStreamReader(
					context.getAssets().open("museum.xml")));
			InputSource inputSource = new InputSource(br);
			ach = new AntiquesContentHandler(this);
			xmlReader.setContentHandler(ach);
			xmlReader.parse(inputSource);
			
			Log.d("dhdebug", "Computer: "+computers.size());
			Log.d("dhdebug", "Components: "+components.size());
			Log.d("dhdebug", "Storages: "+storages.size());

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			Log.e("dhdebug", "Error parsing xml: "+fnfe.getMessage());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			Log.e("dhdebug", "Error parsing xml: "+ioe.getMessage());
		} catch (SAXException saxe) {
			saxe.printStackTrace();
			Log.e("dhdebug", "Error parsing xml: "+saxe.getMessage());
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			Log.e("dhdebug", "Error parsing xml: "+pce.getMessage());
		}
	}
	
	/**
	 * Adds a Computer object to the list of computers
	 * 
	 * @param c the Computer object to add
	 */
	public void addComupter(Computer c) {
		computers.add(c);
	}
	
	/**
	 * Adds a Component object to the list of components
	 * 
	 * @param c the Component object to add
	 */
	public void addComponents(Component c) {
		components.add(c);
	}
	
	/**
	 * Adds a Storage object to the list of storages
	 * 
	 * @param s the Storage object to add
	 */
	public void addStorage(Storage s) {
		storages.add(s);
	}
	
	/**
	 * Adds a InnerlifeComponent to the list of innerlifecomponents
	 * 
	 * @param ic the InnerlifeComponent object to add
	 */
	public void addInnerlifeComponent(InnerlifeComponent ic) {
		Log.d("dhdebug", "IC with ID: "+ic.getId()+"und Name: "+ic.getName()+" added!");
		Computer c = getComputer(ic.getParentId());
		c.addComponent(ic);
	}
	
	/**
	 * Returns a Computer object with the given name
	 * 
	 * @param name the name of the computer
	 * @return a Computer object or null if nothing is found
	 */
	public Computer getComputer(String name) {
		for(Computer c : computers) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Returns a Computer object with the given id
	 * 
	 * @param id the id of the computer
	 * @return a Computer object or null if nothing is found
	 */
	public Computer getComputer(int id) {
		for(Computer c : computers) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Returns a Component object with the given name
	 * 
	 * @param name the name of the component
	 * @return a Component object or null if nothing is found
	 */
	public Component getComponent(String name) {
		for(Component c : components) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Returns a Component object with the given id
	 * 
	 * @param id the id of the component
	 * @return a Component object or null if nothing is found
	 */
	public Component getComponent(int id) {
		for(Component c : components) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Returns the list with the tags of an Component object wit hthe given id
	 * 
	 * @param id the id of the component
	 * @return an ArrayList with the Tags or null if nothing is found
	 */
	public ArrayList<Tag> getTags(int id) {
		for(Component c : components) {
			if(c.getId() == id) {
				return c.getTags();
			}
		}
		return null;
	}
	
	/**
	 * Returns a Storage object with the given name
	 * 
	 * @param name the name of the storage
	 * @return a Storage object or null if nothing is found
	 */
	public Storage getStorage(String name) {
		for(Storage s : storages) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Returns a Storage object with the given id
	 * 
	 * @param id the id of the storage
	 * @return a Storage object or null if nothing is found
	 */
	public Storage getStorage(int id) {
		for(Storage s : storages) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * Returns the ids of the InnerlifeComponents of a computer
	 * 
	 * @param parentid the id of the parent computer
	 * @return an  integer array with the ids or null if nothing is found
	 */
	public int[] getInnerlifeComponentsIDs(int parentid) {
		ArrayList<InnerlifeComponent> iC = null;
		for(Computer c : computers) {
			if(c.getId() == parentid) {
				iC = c.getComponents();
				int[] temp = new int[iC.size()];
				for(int i = 0; i < iC.size(); i++) {
					temp[i] = iC.get(i).getId();
				}
				return temp;
			}
		}
		return null;
		
	}
	
	/**
	 * Returns a InnerlifeComponent object with the given name and parentid
	 * 
	 * @param parentid the id of the parent computer
	 * @param name the name of the innerlifecomponent
	 * @return a InnerlifeComponent object or null if nothing is found
	 */
	public InnerlifeComponent getInnerlifeComponent(int parentid, String name) {
		
		Computer c = getComputer(parentid);
		
		for(InnerlifeComponent iC : c.getComponents()) {
			if(iC.getName().equals(name)) {
				return iC;
			}
		}
		return null;
	}
	
	/**
	 * Returns a InnerlifeComponent object with the given id and parentid
	 * 
	 * @param parentid the id of the parent computer
	 * @param id the id of the innerlifecomponent
	 * @return a InnerlifeComponent object or null if nothing is found
	 */
	public InnerlifeComponent getInnerlifeComponent(int parentid, int id) {
		
		Computer c = getComputer(parentid);
		
		for(InnerlifeComponent iC : c.getComponents()) {
			if(iC.getId() == id) {
				return iC;
			}
		}
		return null;
	}
}
