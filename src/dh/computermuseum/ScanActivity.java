package dh.computermuseum;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class ScanActivity extends Activity implements View.OnClickListener {
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	Camera camera;
	private boolean inPreviewMode = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		initUI();
		setupClickListener(); 
	}
	
	private void initUI() {
		initCameraPreview();
	}
	
	private void setupClickListener() {
		surfaceView.setOnClickListener(this);
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
    }

	public void onResume() {
		super.onResume();
		camera = Camera.open();
		camera.setDisplayOrientation(90);
		startCameraPreview();
	}

	public void onPause() {
		if (inPreviewMode) {
			camera.stopPreview();
		}
		camera.release();
		camera = null;
		inPreviewMode = false;
		super.onPause();
	}

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
	};
}