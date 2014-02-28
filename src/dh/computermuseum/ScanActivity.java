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
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	Camera camera;
	private boolean inPreviewMode = false;
	
	Context context = this;
	
	private IGeometry mModel;
	
	private MetaioSDKCallbackHandler mCallbackHandler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.scan);
		//initUI();
		//setupClickListener();
		mCallbackHandler = new MetaioSDKCallbackHandler();
	}
	/*
	private void initUI() {
		initCameraPreview();
	}
	
	private void setupClickListener() {
		TextView scan = (TextView) findViewById(R.id.shortScan);
		scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mModel.setVisible(true);
				mCallbackHandler.onTrackingEvent(metaioSDK.getTrackingValues());
			}
		});
		//surfaceView.setOnClickListener(this);
	}
	
	
	private void initCameraPreview() {
		surfaceView = (SurfaceView) findViewById(R.id.cameraPreview);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(surfaceCallback);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	
	
	public void onClick(View view) {
    	switch (view.getId()) {
			case  R.id.cameraPreview:
				Intent i = new Intent(ScanActivity.this, DetailActivity.class);
			    startActivity(i);
				break;
			default:
				break;
		}
    }*/

	public void onResume() {
		super.onResume();
		/*camera = Camera.open();
		camera.setDisplayOrientation(90);
		startCameraPreview();*/
	}

	public void onPause() {
		/*if (inPreviewMode) {
			camera.stopPreview();
		}
		camera.release();
		camera = null;
		inPreviewMode = false;*/
		super.onPause();
	}
	/*
	private void displayCameraPreview(int width, int height) {
		if (camera != null && surfaceHolder.getSurface() != null) {
			try {
				camera.setPreviewDisplay(surfaceHolder);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private void startCameraPreview() {
		if (camera != null) {
			camera.startPreview();
			inPreviewMode = true;
		}
	}

	SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
		public void surfaceCreated(SurfaceHolder holder) {
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			displayCameraPreview(width, height);
			startCameraPreview();
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
		}
	};*/

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
			String trackingConfigFile = AssetsManager.getAssetPath("3dmap/desktop.zip");
			
			//Log.d("DEBUG", trackingConfigFile);
			
			// Assigning tracking configuration
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile); 
			MetaioDebug.log("Tracking data loaded: " + result); 
	        
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
				else
					Log.d("METAIO", "METAIOMAN ist eine null!");
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "+metaioManModel);
			}
			
			mModel.setVisible(true);
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
				}
			});
		}
		/*
		@Override
		public void onTrackingEvent(TrackingValuesVector values) {
			
			super.onTrackingEvent(values);
			
			TrackingValues tv = values.get(0);
			
			//ETRACKING_STATE state = ETRACKING_STATE.ETS_FOUND;
			
			if(tv.isTrackingState(ETRACKING_STATE.ETS_FOUND)) {
				mModel.setVisible(true);
			}
			
			CharSequence text = "Tracking Event fired!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}*/
	}
	
}