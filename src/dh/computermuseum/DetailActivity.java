package dh.computermuseum;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Shows a Detail Screen to a computer or a component/board
 * 
 * @author Johannes Lengdobler, Simon Provinsky, Timo Schaschek, Manuel Krapf
 * @version 1.0
 */
public class DetailActivity extends Activity {
	
	private Data data;
	private int id;
	private String type;
	
	private Computer computer;
	private Component component;
	
	private TextView name;
	private TextView date;
	private TextView comp;
	
	private TextView desc;
	
	private TextView pos1val;
	private TextView pos1tag;
	private TextView pos2val;
	private TextView pos2tag;
	private TextView pos3val;
	private TextView pos3tag;
	private TextView pos4val;
	private TextView pos4tag;
	private TextView pos5val;
	private TextView pos5tag;
	private TextView pos6val;
	private TextView pos6tag;
	
	private ImageView img;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
	
		data = new Data(this);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getInt("id");
		type = extras.getString("type");
		
		init();
		
	}
	
	/**
	 * Initialize the UI and decides if a computer or a component is shown
	 */
	private void init() {
		
		initUI();
		
		if(type.equals("computer")) {
			computer = data.getComputer(id);
			initComputer();
		}
		else if(type.equals("component")) {
			component = data.getComponent(id);
			initComponent();
		}
		
	}
	
	/**
	 * Initialize the UI Views
	 */
	private void initUI() {
		
		name = (TextView) findViewById(R.id.textView_named);
		date = (TextView) findViewById(R.id.textView_dated);
		comp = (TextView) findViewById(R.id.textView_company);
		desc = (TextView) findViewById(R.id.textView_description);
		
		pos1tag = (TextView) findViewById(R.id.detailtag_pos1);
		pos2tag = (TextView) findViewById(R.id.detailtag_pos2);
		pos3tag = (TextView) findViewById(R.id.detailtag_pos3);
		pos4tag = (TextView) findViewById(R.id.detailtag_pos4);
		pos5tag = (TextView) findViewById(R.id.detailtag_pos5);
		pos6tag = (TextView) findViewById(R.id.detailtag_pos6);
		
		pos1val = (TextView) findViewById(R.id.detailval_pos1);
		pos2val = (TextView) findViewById(R.id.detailval_pos2);
		pos3val = (TextView) findViewById(R.id.detailval_pos3);
		pos4val = (TextView) findViewById(R.id.detailval_pos4);
		pos5val = (TextView) findViewById(R.id.detailval_pos5);
		pos6val = (TextView) findViewById(R.id.detailval_pos6);
		
		img = (ImageView) findViewById(R.id.imageView_pic);
	}
	
	/**
	 * Sets the data for a computer to the different Views
	 */
	private void initComputer() {
		
		name.setText(computer.getName());
		date.setText(computer.getReleaseDate());
		comp.setText(computer.getProducer());
		desc.setText(computer.getDescription());
		
		pos1tag.setText(R.string.detail_os);
		pos2tag.setText(R.string.detail_memory);
		pos3tag.setText(R.string.detail_ram);
		pos4tag.setText(R.string.detail_cpu);
		pos5tag.setText(R.string.detail_special);
		pos6tag.setText("");
		
		pos1val.setText(computer.getOs());
		pos2val.setText(computer.getMemory());
		pos3val.setText(computer.getRam());
		pos4val.setText(computer.getCpu());
		pos5val.setText(computer.getSpecial());
		pos6val.setText("");
		
		Resources res = getResources();
		String tempImgName = computer.getImg();
		int tempResId = res.getIdentifier(tempImgName, "drawable", getPackageName());
		Drawable tempImg = res.getDrawable(tempResId);
		img.setImageDrawable(tempImg);
	}
	
	/**
	 * Sets the data of a component to the different Views
	 */
	private void initComponent() {
		
		name.setText(component.getName());
		date.setText(component.getReleaseDate());
		comp.setText(component.getProducer());
		desc.setText(component.getDescription());
		
		pos1tag.setText(R.string.detailcomp_type);
		pos2tag.setText(R.string.detailcomp_freq);
		pos3tag.setText(R.string.detailcomp_memory);
		pos4tag.setText(R.string.detailcomp_cpu);
		pos5tag.setText(R.string.detailcomp_register);
		pos6tag.setText(R.string.detailcomp_interfaces);
		
		pos1val.setText(component.getFunctionType());
		pos2val.setText(component.getFrequency());
		pos3val.setText(component.getMemory());
		pos4val.setText(component.getCPU());
		pos5val.setText(component.getRegister());
		pos6val.setText(component.getInterfaces());
		
		Log.d("dhdebug", component.getImg());
		
		Resources res = getResources();
		String tempImgName = component.getImg();
		int tempResId = res.getIdentifier(tempImgName, "drawable", getPackageName());
		Drawable tempImg = res.getDrawable(tempResId);
		img.setImageDrawable(tempImg);
	}
	
}