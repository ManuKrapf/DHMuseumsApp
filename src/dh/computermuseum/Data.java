package dh.computermuseum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

public class Data {
	
	Context context; 
	
	private List<Antique> antiques;
	private AntiquesContentHandler ach;
	
	public Data(Context c) {
		context = c;
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
			ach = new AntiquesContentHandler();
			xmlReader.setContentHandler(ach);
			xmlReader.parse(inputSource);
			
			antiques = ach.getAntiques();
			Log.d("XML", ach.getAntiques().toString());

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

}
