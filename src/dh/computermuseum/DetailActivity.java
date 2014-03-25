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
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	private Data data;
	private int id;
	private String type;
	
	private Computer computer;
	
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	
		data = new Data(this);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getInt("id");
		
		init();
		
	}
	
	private void init() {
		
		initUI();
		computer = data.getComputer(id);
		initComputer();
		
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
		
		Resources res = getResources();
		String tempImgName = computer.getImg();
		int tempResId = res.getIdentifier(tempImgName, "drawable", getPackageName());
		Drawable tempImg = res.getDrawable(tempResId);
		img.setImageDrawable(tempImg);
	}
	
}