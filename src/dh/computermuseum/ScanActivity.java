package dh.computermuseum;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.*;
import com.metaio.sdk.jni.ETRACKING_STATE;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.MetaioSDKConstants;
import com.metaio.sdk.jni.TrackingValues;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;

public class ScanActivity extends ARViewActivity {
	
	Context context = this;
	private Data data;
	
	private int id = 0;
	
	private RelativeLayout layout;
	private ImageView details;
	private ImageView video;
	private TextView name;
	private TextView comp;
	private TextView datebefore;
	private TextView date;
	private TextView dateafter1;
	private TextView dateafter2;
	
	private LinearLayout storageline;
	private ImageView closesl;
	
	private LinearLayout gobefore;
	private ImageView beforeimage;
	private TextView beforetext;
	private LinearLayout goafter;
	private ImageView afterimage;
	private TextView aftertext;
	
	private TextView storageName;
	private TextView storageDev;
	private TextView storageDate;
	private TextView storageType;
	private TextView storageCapa;
	private TextView storageMat;
	private TextView storageLife;
	private TextView storageSize;
	
	/*
	private IGeometry mModel;
	private IGeometry mModel2;
	*/
	private MetaioSDKCallbackHandler mCallbackHandler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		data = new Data(this);
		mCallbackHandler = new MetaioSDKCallbackHandler();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	@Override
	protected int getGUILayout() {
		return R.layout.scan;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		return mCallbackHandler;
	}

	@Override
	protected void loadContents() {
		try
		{
			AssetsManager.extractAllAssets(getApplicationContext(), BuildConfig.DEBUG);
			// Getting a file path for tracking configuration XML file
			String trackingConfigFile = AssetsManager.getAssetPath("trackingdata/Tracking.xml");
			
			//Log.d("DEBUG", trackingConfigFile);
			
			// Assigning tracking configuration
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub
		
	}
	
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback 
	{
		
		
		
		@Override
		public void onSDKReady()
		{
			// show GUI
			runOnUiThread(new Runnable() 
			{
				@Override
				public void run() 
				{
					mGUIView.setVisibility(View.VISIBLE);
					
					layout = (RelativeLayout) findViewById(R.id.timelineview);
					
					details = (ImageView) findViewById(R.id.showDetailsButton);
					video = (ImageView) findViewById(R.id.showVideoButton);
					
					details.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							Intent i = new Intent(ScanActivity.this, DetailActivity.class);
							i.putExtra("id", id);
						    startActivity(i);
							
						}
					});
					
					// Tag with Timeline
					name = (TextView) findViewById(R.id.tl_nametag);
					comp = (TextView) findViewById(R.id.tl_companytag);
					datebefore = (TextView) findViewById(R.id.tl_datetagbefore);
					date = (TextView) findViewById(R.id.tl_datetag);
					dateafter1 = (TextView) findViewById(R.id.tl_datetagafter1);
					dateafter2 = (TextView) findViewById(R.id.tl_datetagafter2);
					
					// Storage line
					storageline = (LinearLayout) findViewById(R.id.storageline);
					closesl = (ImageView) findViewById(R.id.close);
					
					gobefore = (LinearLayout) findViewById(R.id.go_before);
					beforeimage = (ImageView) findViewById(R.id.before_image);
					beforetext = (TextView) findViewById(R.id.before_text);
					
					storageName = (TextView) findViewById(R.id.actual_name);
					storageDev = (TextView) findViewById(R.id.actual_dev);
					storageDate = (TextView) findViewById(R.id.actual_date);
					storageType = (TextView) findViewById(R.id.actual_type);
					storageCapa = (TextView) findViewById(R.id.actual_capa);
					storageMat = (TextView) findViewById(R.id.actual_material);
					storageLife = (TextView) findViewById(R.id.actual_life);
					storageSize = (TextView) findViewById(R.id.actual_size);
					
					goafter = (LinearLayout) findViewById(R.id.go_after);
					afterimage = (ImageView) findViewById(R.id.after_image);
					aftertext = (TextView) findViewById(R.id.after_text);
					
					closesl.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							storageline.setVisibility(View.GONE);
						}
					});
					
					gobefore.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							showStorageLine(id-1);
							id = id-1;
							
						}
					});
					
					goafter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							showStorageLine(id+1);
							id = id+1;
							
						}
					});
					
				}
			});
		}
		
		@Override
		public void onTrackingEvent(TrackingValuesVector values) {
			
			super.onTrackingEvent(values);
			
			//loadModel();
			
			if (values.size() > 0) {
				
				if(values.get(0).getState() == ETRACKING_STATE.ETS_FOUND) {
					Log.d("dhdebug", values.get(0).getCosName()+" is found");
					
					if(values.get(0).getCosName().equals("watch_1")) {
						Log.d("dhdebug", "ID watch: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(1));
						id = 1;
					}
					else if(values.get(0).getCosName().equals("keyboard_2")) {
						Log.d("dhdebug", "ID tastatur: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(2));
						id = 2;
					}
					else if(values.get(0).getCosName().equals("overhead_3")) {
						Log.d("dhdebug", "ID overhead: "+values.get(0).getCoordinateSystemID());
						showStorageLine(11);
						id = 11;
					}
					else if(values.get(0).getCosName().equals("display_4")) {
						Log.d("dhdebug", "ID display: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(4));
						id = 4;
					}
					else if(values.get(0).getCosName().equals("tablet_5")) {
						Log.d("dhdebug", "ID display: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(1));
						id = 1;
					}
					else if(values.get(0).getCosName().equals("book_6")) {
						Log.d("dhdebug", "ID display: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(2));
						id = 2;
					}
					else if(values.get(0).getCosName().equals("glasses_7")) {
						Log.d("dhdebug", "ID display: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(3));
						id = 3;
					}
				}
				else if(values.get(0).getState() == ETRACKING_STATE.ETS_LOST) {
					id = 0;
					//hideTimeline();
				}
				else {
					Log.d("dhdebug", "nothing is registered or found");
				}
				/*
				Log.d("dhdebug", "Values: "+values.size());
				Log.d("dhdebug", "Name 0: "+values.get(0).getCosName());
				Log.d("dhdebug", "State 0: "+values.get(0).getState());
				if(values.size() >= 2) {
					Log.d("dhdebug", "Name 1: "+values.get(1).getCosName());
					Log.d("dhdebug", "State 1: "+values.get(1).getState());
				}*/
				
			}
			
		}
	}
	
	private void showStorageLine(int id) {
		
		Storage before = null;
		if(id > 7) {
			before = data.getStorage(id-1);
		}
		
		Storage after = null;
		if(id < 18) {
			after = data.getStorage(id);
		}
		
		final Storage sbefore = before;
		final Storage s = data.getStorage(id);
		final Storage safter = after;
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				storageline.setVisibility(View.VISIBLE);
				
				if(sbefore != null) {
					gobefore.setVisibility(View.VISIBLE);
					beforetext.setText(sbefore.getName());
				}
				else {
					gobefore.setVisibility(View.INVISIBLE);
				}
				
				storageName.setText(s.getName());
				storageDev.setText(s.getDeveloper());
				storageDate.setText(s.getReleaseDate());
				storageType.setText(s.getType());
				storageCapa.setText(s.getCapacity());
				storageMat.setText(s.getMaterial());
				storageLife.setText(s.getEndurance());
				storageSize.setText(s.getSize());
				
				if(safter != null) {
					goafter.setVisibility(View.VISIBLE);
					aftertext.setText(safter.getName());
				}
				else {
					goafter.setVisibility(View.INVISIBLE);
				}
				
			}
		});
		
	}
	/*
	private void showTimeline(Antique temp) {
		
		final Antique ant = temp;
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				layout.setVisibility(View.VISIBLE);
				name.setText(ant.getName());
				comp.setText(ant.getProducer());
				datebefore.setText("1800");
				date.setText(ant.getReleaseDate());
				dateafter1.setText("2000");
				dateafter2.setText("2013");
			}
		});
		
	}
	
	private void hideTimeline() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				layout.setVisibility(View.GONE);
			}
		});
		
	}
	
	private Antique getDataFromXML(int type, int id) {
		
		switch(type) {
			case 1: Computer c = data.getComputer(id);
					return c;
				break;
			case 2: 
				break;
			case 3: 
				break;
		}
		
		Antique ant = data.getAntique(id);
		Log.d("dhdebug", "Antique: ID: "+ant.getId()+", Name: "+ant.getName()+", Date: "+ant.getReleaseDate()+", Type: "+ant.getType());
		
		return ant;//"Antique: ID: "+ant.getId()+", Name: "+ant.getName()+", Date: "+ant.getReleaseDate()+", Type: "+ant.getType();
		
	}*/
	
}