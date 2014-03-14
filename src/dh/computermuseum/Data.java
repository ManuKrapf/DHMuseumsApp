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
			
			//antiques = ach.getAntiques();
			//Log.d("XML", ach.getAntiques().toString());

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
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
	/*
	public Antique getAntique(String name) {
		for(Antique a : antiques) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public Antique getAntique(int id) {
		for(Antique a : antiques) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	*/
}
