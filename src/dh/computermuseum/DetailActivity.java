package dh.computermuseum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends Activity {
	private AntiquesContentHandler ach;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	
		//initXMLParser();
		initUI();
		setupClickListener(); 
		
	}
	
	private void initUI() {
		
	}
	
	private void setupClickListener() {
		
	}
	
	private void initXMLParser() {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser newSAXParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = newSAXParser.getXMLReader();
			// read XML input file here:
			BufferedReader br = new BufferedReader(new InputStreamReader(
					getAssets().open("testfile.xml")));
			InputSource inputSource = new InputSource(br);
			ach = new AntiquesContentHandler();
			xmlReader.setContentHandler(ach);
			xmlReader.parse(inputSource);
			
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
}