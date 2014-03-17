package dh.computermuseum;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.*;
import com.metaio.sdk.jni.ETRACKING_STATE;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Rotation;
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
	
	private IGeometry movie;
	private IGeometry g_tag1;
	private IGeometry g_tag2;
	private IGeometry g_tag3;
	private IGeometry g_tag4;
	
	/*
	private IGeometry mModel;
	private IGeometry mModel2;
	*/
	private MetaioSDKCallbackHandler mCallbackHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		data = new Data(this);
		mCallbackHandler = new MetaioSDKCallbackHandler();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
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
					
					final Animation fadeInAnim = AnimationUtils.loadAnimation(context, R.anim.fadein);
					final Animation fadeOutAnim = AnimationUtils.loadAnimation(context, R.anim.fadeout);
					
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
							
							// animation fading
							gobefore.startAnimation(fadeOutAnim);
							if(id > 7) {
								gobefore.startAnimation(fadeInAnim);
							}
							
						}
					});
					
					goafter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							showStorageLine(id+1);
							id = id+1;
							
							// animation fading
							goafter.startAnimation(fadeOutAnim);
							if(id < 17) {
								goafter.startAnimation(fadeInAnim);
							}
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
					
					if(values.get(0).getCosName().equals("overhead_1")) {
						Log.d("dhdebug", "ID overhead: "+values.get(0).getCoordinateSystemID());
						//showStorageLine(11);
						loadMovie(values.get(0).getCoordinateSystemID());
						id = 11;
					}
					else if(values.get(0).getCosName().equals("book_2")) {
						Log.d("dhdebug", "ID book: "+values.get(0).getCoordinateSystemID());
						//showTimeline(getDataFromXML(2));
						id = 2;
					}
					else if(values.get(0).getCosName().equals("yellow_book_3")) {
						Log.d("dhdebug", "ID yellowbook: "+values.get(0).getCoordinateSystemID());
						showMBTags(values.get(0).getCoordinateSystemID());
						//loadMovie(values.get(0).getCoordinateSystemID());
						id = 11;
					}
				}
				else if(values.get(0).getState() == ETRACKING_STATE.ETS_LOST) {
					//id = 0;
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
		if(id < 17) {
			after = data.getStorage(id+1);
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
					
					Resources res = getResources();
					String tempImgName = sbefore.getImg();
					int tempResId = res.getIdentifier(tempImgName, "drawable", getPackageName());
					Drawable tempImg = res.getDrawable(tempResId);
					beforeimage.setImageDrawable(tempImg);
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
					
					Resources res = getResources();
					String tempImgName = safter.getImg();
					int tempResId = res.getIdentifier(tempImgName, "drawable", getPackageName());
					Drawable tempImg = res.getDrawable(tempResId);
					afterimage.setImageDrawable(tempImg);
				}
				else {
					goafter.setVisibility(View.INVISIBLE);
				}
				
			}
		});
		
	}
	
	private void showMBTags(int cosid) {
		
		final String tag1 = AssetsManager.getAssetPath("Assets/mbtag_southbridge.png");
		final String tag2 = AssetsManager.getAssetPath("Assets/mbtag_northbridge.png");
		final String tag3 = AssetsManager.getAssetPath("Assets/mbtag_ram.png");
		final String tag4 = AssetsManager.getAssetPath("Assets/mbtag_cpu.png");
		
		if (tag1 != null)
		{
			g_tag1 = metaioSDK.createGeometryFromImage(tag1, true, true);
			if (g_tag1 != null)
			{
				//g_tag1.setScale(5f);
				g_tag1.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
				g_tag1.setTranslation(new Vector3d(100,0,2));
				//movie.setTransparency(0.1f);
				g_tag1.setCoordinateSystemID(cosid);
				
				MetaioDebug.log("Loaded geometry "+tag1);
			}
			else {
				MetaioDebug.log(Log.ERROR, "Error loading geometry: "+tag1);
			}
		}
		
		if (tag2 != null)
		{
			g_tag2 = metaioSDK.createGeometryFromImage(tag2, true, true);
			if (g_tag2 != null)
			{
				//movie.setScale(1.5f);
				g_tag2.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
				g_tag2.setTranslation(new Vector3d(150,0,10));
				//movie.setTransparency(0.1f);
				g_tag2.setCoordinateSystemID(cosid);
				
				MetaioDebug.log("Loaded geometry "+tag2);
			}
			else {
				MetaioDebug.log(Log.ERROR, "Error loading geometry: "+tag2);
			}
		}
		
		if (tag3 != null)
		{
			g_tag3 = metaioSDK.createGeometryFromImage(tag3, true, true);
			if (g_tag3 != null)
			{
				//movie.setScale(1.5f);
				g_tag3.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
				g_tag3.setTranslation(new Vector3d(0,0,0));
				//movie.setTransparency(0.1f);
				g_tag3.setCoordinateSystemID(cosid);
				
				MetaioDebug.log("Loaded geometry "+tag3);
			}
			else {
				MetaioDebug.log(Log.ERROR, "Error loading geometry: "+tag3);
			}
		}
		
		if (tag4 != null)
		{
			g_tag4 = metaioSDK.createGeometryFromImage(tag4);
			if (g_tag4 != null)
			{
				//movie.setScale(1.5f);
				g_tag4.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
				g_tag4.setTranslation(new Vector3d(-120,0,-20));
				//movie.setTransparency(0.1f);
				g_tag4.setCoordinateSystemID(cosid);
				
				MetaioDebug.log("Loaded geometry "+tag4);
			}
			else {
				MetaioDebug.log(Log.ERROR, "Error loading geometry: "+tag4);
			}
		}
		
	}
	
	private void loadMovie(int cosid) {
		
		final String moviePath = AssetsManager.getAssetPath("Assets/videoc128.3g2");
		
		if (moviePath != null)
		{
			movie = metaioSDK.createGeometryFromMovie(moviePath, false, true);
			if (movie != null)
			{
				movie.setScale(1.5f);
				movie.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));//new Rotation(0f, 0f, (float)-Math.PI/2));
				movie.setTransparency(0.1f);
				movie.setCoordinateSystemID(cosid);
				movie.startMovieTexture(true);
				
				MetaioDebug.log("Loaded geometry "+moviePath);
			}
			else {
				MetaioDebug.log(Log.ERROR, "Error loading geometry: "+moviePath);
			}
		}
		
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
