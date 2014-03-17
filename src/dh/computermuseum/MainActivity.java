package dh.computermuseum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    /** Called when the activity is first created. */
	private Button buttonScan;
	private TextView textGoto;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
        setupClickListener();      
    }
    
    private void initUI() {
    	buttonScan = (Button) findViewById(R.id.startScanningButton1);
    	textGoto = (TextView) findViewById(R.id.textMuseumGoto);
    }
    
    private void setupClickListener() {
    	buttonScan.setOnClickListener(this);
    	textGoto.setOnClickListener(this);   	
    }
    
    @Override
	public void onClick(View view) {
    	switch (view.getId()) {
			case  R.id.startScanningButton1:
				Intent i = new Intent(MainActivity.this, ScanActivity.class);
			    startActivity(i);
				break;
			case  R.id.textMuseumGoto:
				String uri = "http://maps.google.de/maps?oe=utf-8&client=firefox-a&q=google+maps+regensburg+landshuter+stra%C3%9Fe+4&ie=UTF-8&hq=&hnear=0x479fc1a0f33efec9:0xe259d0ef710115cf,Landshuter+Stra%C3%9Fe+4,+D-93047+Regensburg&gl=de&ei=mfDrUoOUAYaZtAa-7IDABg&ved=0CC4Q8gEwAA";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
				break;
			default:
				break;
		}
    }
}