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
	TextView text;
	
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
			String trackingConfigFile = AssetsManager.getAssetPath("3dmap/3dmaps.zip");
			
			//Log.d("DEBUG", trackingConfigFile);
			
			// Assigning tracking configuration
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result);
			
			//loadModel();
			
	        /*
			// Getting a file path for a 3D geometry
			String metaioManModel = AssetsManager.getAssetPath("Assets/metaioman.md2");			
			if (metaioManModel != null)
			{
				Log.d("METAIO", "File found!!!");
				
				// Loading 3D geometry
				mModel = metaioSDK.createGeometry(metaioManModel);
				if (mModel != null) 
				{
					Log.d("METAIO", "Model nicht null");
					// Set geometry properties
					mModel.setScale(new Vector3d(4.0f, 4.0f, 4.0f));
					
				}
				else {
					Log.d("METAIO", "METAIOMAN ist eine null!");
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "+metaioManModel);
				}
			}*/
			
			//mModel.setVisible(true);
			//mCallbackHandler.onTrackingEvent(metaioSDK.getTrackingValues());
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
					text = (TextView) findViewById(R.id.timeline);
				}
			});
		}
		
		@Override
		public void onTrackingEvent(TrackingValuesVector values) {
			
			super.onTrackingEvent(values);
			
			loadModel();
			
			//TrackingValues tv = values.get(0);
			
			//ETRACKING_STATE state = ETRACKING_STATE.ETS_FOUND;
			
			if (values.size() > 0) {
				
				String temp = "";
				
				if(values.get(0).getState() == ETRACKING_STATE.ETS_FOUND) {
					Log.d("dhdebug", values.get(0).getCosName()+" is found");
					
					if(values.get(0).getCosName().equals("watch_1")) {
						Log.d("dhdebug", "ID watch: "+values.get(0).getCoordinateSystemID());
						temp = getDataFromXML(1);
					}
					else if(values.get(0).getCosName().equals("keyboard_2")) {
						Log.d("dhdebug", "ID tastatur: "+values.get(0).getCoordinateSystemID());
						temp = getDataFromXML(2);
					}
					else if(values.get(0).getState() == ETRACKING_STATE.ETS_LOST) {
						//text.setVisibility(View.INVISIBLE);
					}
					
					final String textval = temp;
					
					runOnUiThread(new Runnable() 
					{
						@Override
						public void run() 
						{
							text.setText(textval);
						}
					});
					
					//loadModel();
				}
				else {
					Log.d("dhdebug", "nothing is registered or found");
				}
				
				Log.d("dhdebug", "Values: "+values.size());
				Log.d("dhdebug", "Name 0: "+values.get(0).getCosName());
				Log.d("dhdebug", "State 0: "+values.get(0).getState());
				if(values.size() >= 2) {
					Log.d("dhdebug", "Name 1: "+values.get(1).getCosName());
					Log.d("dhdebug", "State 1: "+values.get(1).getState());
				}
				
			}
			
			/*
			if(tv.isTrackingState(ETRACKING_STATE.ETS_FOUND)) {
				mModel.setVisible(true);
			}
			
			
			CharSequence text = "Tracking Event fired!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			*/
		}
	}
	
	private void loadModel() {
		
		try
		{
	        
			if(mModel != null) {
				metaioSDK.unloadGeometry(mModel);
			}
			
			if(mModel2 != null) {
				metaioSDK.unloadGeometry(mModel2);
			}
			
			// Getting a file path for a 3D geometry
			String metaioManModel = AssetsManager.getAssetPath("Assets/metaioman.md2");			
			if (metaioManModel != null)
			{
				Log.d("METAIO", "File 1 found!!!");
				
				// Loading 3D geometry
				mModel = metaioSDK.createGeometry(metaioManModel);
				if (mModel != null) 
				{
					Log.d("METAIO", "Model 1 nicht null");
					// Set geometry properties
					mModel.setScale(new Vector3d(4.0f, 4.0f, 4.0f));
					mModel.setCoordinateSystemID(1);
					
				}
				else {
					Log.d("METAIO", "METAIOMAN ist eine null!");
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "+metaioManModel);
				}
			}
			
			String otherModel = AssetsManager.getAssetPath("arel2/bild.jpg");			
			if (otherModel != null)
			{
				Log.d("METAIO", "File 2 found!!!");
				
				
				
				// Loading 3D geometry
				mModel2 = metaioSDK.createGeometryFromImage(otherModel);
				if (mModel2 != null) 
				{
					Log.d("METAIO", "Model 2 nicht null");
					// Set geometry properties
					//mModel.setScale(new Vector3d(4.0f, 4.0f, 4.0f));
					mModel2.setCoordinateSystemID(2);
					
				}
				else {
					Log.d("METAIO", "METAIOMAN ist eine null!");
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "+otherModel);
				}
			}
			
			//mModel.setVisible(true);
			//mCallbackHandler.onTrackingEvent(metaioSDK.getTrackingValues());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private String getDataFromXML(int id) {
		
		String name = "";
		
		switch(id) {
			case 1: name = "Yost No. 15";
				break;
			case 2: name = "Toshiba T3200SX";
				break;
			case 3: 
				break;
			case 4: 
				break;
			default: name = "Megacomputer";
				break;
		}
		
		Antique ant = data.getAntique(name);
		Log.d("dhdebug", "Antique: ID: "+ant.getId()+", Name: "+ant.getName()+", Date: "+ant.getReleaseDate());
		
		return "Antique: ID: "+ant.getId()+", Name: "+ant.getName()+", Date: "+ant.getReleaseDate();
		
		//text.setVisibility(View.VISIBLE);
	}
	
}