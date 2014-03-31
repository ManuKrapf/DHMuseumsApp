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

public class Data {
	
	Context context; 
	
	private ArrayList<Computer> computers;
	private ArrayList<Component> components;
	private ArrayList<Storage> storages;
	private AntiquesContentHandler ach;
	
	public Data(Context c) {
		context = c;
		computers = new ArrayList<Computer>();
		components = new ArrayList<Component>();
		storages = new ArrayList<Storage>();
		initXMLParser();
	}
	
	private void initXMLParser() {
		Log.d("dhdebug", "initXML is called!");
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
	
	public void addComupter(Computer c) {
		computers.add(c);
	}
	
	public void addComponents(Component c) {
		components.add(c);
	}
	
	public void addStorage(Storage s) {
		storages.add(s);
	}
	
	public void addInnerlifeComponent(InnerlifeComponent ic) {
		Computer c = getComputer(ic.getParentId());
		c.addComponent(ic);
		Log.d("dhdebug", "IC added: ID: "+ic.getId()+", PID: "+ic.getParentId()+", CID: "+c.getId());
	}
	
	public Computer getComputer(String name) {
		for(Computer c : computers) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	public Computer getComputer(int id) {
		for(Computer c : computers) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public Component getComponent(String name) {
		for(Component c : components) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	public Component getComponent(int id) {
		for(Component c : components) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Tag> getTags(int id) {
		for(Component c : components) {
			if(c.getId() == id) {
				return c.getTags();
			}
		}
		return null;
	}
	
	public Storage getStorage(String name) {
		for(Storage s : storages) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	public Storage getStorage(int id) {
		for(Storage s : storages) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
	
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
	
	public InnerlifeComponent getInnerlifeComponent(int parentid, String name) {
		
		Computer c = getComputer(parentid);
		
		for(InnerlifeComponent iC : c.getComponents()) {
			if(iC.getName().equals(name)) {
				return iC;
			}
		}
		return null;
	}
	
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
