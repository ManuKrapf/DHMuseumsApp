package dh.computermuseum;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
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
	Data data;
	
	RelativeLayout layout;
	TextView name;
	TextView comp;
	TextView datebefore;
	TextView date;
	TextView dateafter1;
	TextView dateafter2;
	
	private IGeometry mModel;
	private IGeometry mModel2;
	
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
					name = (TextView) findViewById(R.id.tl_nametag);
					comp = (TextView) findViewById(R.id.tl_companytag);
					datebefore = (TextView) findViewById(R.id.tl_datetagbefore);
					date = (TextView) findViewById(R.id.tl_datetag);
					dateafter1 = (TextView) findViewById(R.id.tl_datetagafter1);
					dateafter2 = (TextView) findViewById(R.id.tl_datetagafter2);
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
						showTimeline(getDataFromXML(1));
					}
					else if(values.get(0).getCosName().equals("keyboard_2")) {
						Log.d("dhdebug", "ID tastatur: "+values.get(0).getCoordinateSystemID());
						showTimeline(getDataFromXML(2));
					}
					else if(values.get(0).getCosName().equals("overhead_3")) {
						Log.d("dhdebug", "ID overhead: "+values.get(0).getCoordinateSystemID());
						showTimeline(getDataFromXML(3));
					}
					else if(values.get(0).getCosName().equals("display_4")) {
						Log.d("dhdebug", "ID display: "+values.get(0).getCoordinateSystemID());
						showTimeline(getDataFromXML(4));
					}
				}
				else if(values.get(0).getState() == ETRACKING_STATE.ETS_LOST) {
					hideTimeline();
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
	
	private Antique getDataFromXML(int id) {
		
		Antique ant = data.getAntique(id);
		Log.d("dhdebug", "Antique: ID: "+ant.getId()+", Name: "+ant.getName()+", Date: "+ant.getReleaseDate()+", Type: "+ant.getType());
		
		return ant;//"Antique: ID: "+ant.getId()+", Name: "+ant.getName()+", Date: "+ant.getReleaseDate()+", Type: "+ant.getType();
		
	}
	
}