package dh.computermuseum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	private Data data;
	private int id;
	private String type;
	
	private HashMap<String, Integer> types;
	
	private Computer computer;
	private Component component;
	private Storage storage;
	
	private TextView name;
	private TextView date;
	private TextView comp;
	private TextView desc;
	private TextView os;
	private TextView mem;
	private TextView ram;
	private TextView cpu;
	private TextView spec;
	private ImageView img;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	
		data = new Data(this);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getInt("id");
		type = extras.getString("type");
		
		types = new HashMap<String, Integer>();
		
		types.put("computer", 1);
		types.put("component", 2);
		types.put("storage", 3);
		
		init();
		
	}
	
	private void init() {
		
		initUI();
		
		switch(types.get(type)) {
			case 1: computer = new Computer();
					initComputer();
				break;
			case 2: component = new Component();
					initComponent();
				break;
			case 3: storage = new Storage();
					initStorage();
				break;
			default: 
		}
		
	}
	
	private void initUI() {
		name = (TextView) findViewById(R.id.textView_named);
		date = (TextView) findViewById(R.id.textView_dated);
		comp = (TextView) findViewById(R.id.textView_company);
		desc = (TextView) findViewById(R.id.textView_description);
		os = (TextView) findViewById(R.id.textView_os_name);
		mem = (TextView) findViewById(R.id.textView_memory_name);
		ram = (TextView) findViewById(R.id.textView_ram_name);
		cpu = (TextView) findViewById(R.id.textView_cpu_name);
		spec = (TextView) findViewById(R.id.textView_special_name);
		img = (ImageView) findViewById(R.id.imageView_pic);
	}
	
	private void initComputer() {
		name.setText(computer.getName());
		date.setText(computer.getReleaseDate());
		comp.setText(computer.getProducer());
		desc.setText(computer.getDescription());
		os.setText(computer.getOs());
		mem.setText(computer.getMemory());
		ram.setText(computer.getRam());
		cpu.setText(computer.getCpu());
		spec.setText(computer.getSpecial());
		
		img.setImageResource(R.drawable.toshiba_t3200sx);
	}
	
	private void initComponent() {
		name.setText(component.getName());
		date.setText(component.getReleaseDate());
		comp.setText(component.getProducer());
		desc.setText(component.getDescription());
		spec.setText(component.getSpecial());
	}
	
	private void initStorage() {
		//TO-DO
	}
	
}