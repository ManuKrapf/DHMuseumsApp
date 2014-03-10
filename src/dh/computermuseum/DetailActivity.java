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
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	private Data data;
	private int id;
	
	private Antique ant;
	
	private TextView name;
	private TextView date;
	private TextView comp;
	private TextView desc;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	
		data = new Data(this);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getInt("id");
		ant = data.getAntique(id);
		
		initUI();
		//setupClickListener(); 
		
	}
	
	private void initUI() {
		name = (TextView) findViewById(R.id.textView_named);
		date = (TextView) findViewById(R.id.textView_dated);
		comp = (TextView) findViewById(R.id.textView_company);
		desc = (TextView) findViewById(R.id.textView_description);
		
		name.setText(ant.getName());
		date.setText(ant.getReleaseDate());
		comp.setText(ant.getProducer());
		desc.setText(ant.getDescription());
		
	}
	
	private void setupClickListener() {
		
	}
	
}