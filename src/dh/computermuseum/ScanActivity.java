package dh.computermuseum;

import java.util.ArrayList;

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
	
	private LinearLayout innerlifeInfo;
	private ImageView closeInnerlife;
	
	private TextView innerlifeElementName;
	private TextView innerlifeElementDev;
	private TextView innerlifeElementDate;
	private TextView innerlifeElementDesc;
	
	private LinearLayout componentInfo;
	
	private TextView componentElementName;
	private TextView componentElementDev;
	private TextView componentElementDate;
	private TextView componentElementDesc;

	private IGeometry movie;
	private IGeometry g_tag1;
	private IGeometry g_tag2;
	private IGeometry g_tag3;
	private IGeometry g_tag4;
	private IGeometry inner1;
	private IGeometry inner2;
	private IGeometry inner3;
	private IGeometry inner4;
	
	private int actinner = 0;
	
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
		
		Log.d("dhdebug", geometry.toString());
		
		if(geometry.equals(inner1)) {
			Log.d("dhdebug", "geometry is inner 1");
			setInner(inner1, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner2, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner3, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner4, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = 18;
			showInnerlifeComponents(1, actinner);
		}
		else if(geometry.equals(inner2)) {
			Log.d("dhdebug", "geometry is inner 2");
			setInner(inner2, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner3, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner4, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner1, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = 19;
			showInnerlifeComponents(1, actinner);
		}
		else if(geometry.equals(inner3)) {
			Log.d("dhdebug", "geometry is inner 3");
			setInner(inner3, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner4, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner1, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner2, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = 20;
			showInnerlifeComponents(1, actinner);
		}
		else if(geometry.equals(inner4)) {
			Log.d("dhdebug", "geometry is inner 4");
			setInner(inner4, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner1, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner2, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner3, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = 21;
			showInnerlifeComponents(1, actinner);
		}
		
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
					
					// Innerlife information view
					innerlifeInfo = (LinearLayout) findViewById(R.id.innerlifeOverview);
					innerlifeElementName = (TextView) findViewById(R.id.actual_name_innerlife);
					innerlifeElementDev = (TextView) findViewById(R.id.actual_dev_innerlife);
					innerlifeElementDate = (TextView) findViewById(R.id.actual_date_innerlife);
					innerlifeElementDesc = (TextView) findViewById(R.id.actual_desc_innerlife);
					closeInnerlife = (ImageView) findViewById(R.id.close_innerlife);
					
					// Component information view
					componentInfo = (LinearLayout) findViewById(R.id.componentOverview);
					componentElementName = (TextView) findViewById(R.id.actual_name_component);
					componentElementDev = (TextView) findViewById(R.id.actual_name_component);
					componentElementDate = (TextView) findViewById(R.id.actual_name_component);
					componentElementDesc = (TextView) findViewById(R.id.actual_name_component);
					
					closeInnerlife.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							innerlifeInfo.setVisibility(View.GONE);
						}
					});
					
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
						//showComputerTimeline(getComputer(1));
						//showStorageLine(11);
						//showMBTags(values.get(0).getCoordinateSystemID());
						//initInnerlife(1, values.get(0).getCoordinateSystemID());
						//loadMovie(values.get(0).getCoordinateSystemID());
						showComponentView(data.getComponent(5));
						id = 5;
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
	
	private void initInnerlife(int id, int cosid) {
		
		Computer c = data.getComputer(id);
		//ArrayList<InnerlifeComponent> list = c.getComponents();
		
		final String innerfile1 = AssetsManager.getAssetPath("Assets/sd.jpg");//+c.getComponent(1).getImg());
		final String innerfile2 = AssetsManager.getAssetPath("Assets/cdrom.jpg");
		final String innerfile3 = AssetsManager.getAssetPath("Assets/dvd.jpg");
		final String innerfile4 = AssetsManager.getAssetPath("Assets/usb.jpg");
		
		if(innerfile1 != null && inner1 == null) {
			inner1 = metaioSDK.createGeometryFromImage(innerfile1);
			setInner(inner1, 1.3f, new Vector3d(0,0,0), cosid);
		}
		if(innerfile2 != null && inner2 == null) {
			inner2 = metaioSDK.createGeometryFromImage(innerfile2);
			setInner(inner2, 0.8f, new Vector3d(-200,0,-20), cosid);
		}
		if(innerfile3 != null && inner3 == null) {
			inner3 = metaioSDK.createGeometryFromImage(innerfile3);
			setInner(inner3, 0.5f, new Vector3d(0,0,-100), cosid);
		}
		if(innerfile4 != null && inner4 == null) {
			inner4 = metaioSDK.createGeometryFromImage(innerfile4);
			setInner(inner4, 0.8f, new Vector3d(200,0,-20), cosid);
		}
		
		if(actinner == 0) {
			actinner = 18;
		}
		
		showInnerlifeComponents(id, actinner);
	}
	
	private void showInnerlifeComponents(int parentid, int id) {
		
		final InnerlifeComponent iC = data.getInnerlifeComponent(parentid, id);
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				innerlifeInfo.setVisibility(View.VISIBLE);

				innerlifeElementName.setText(iC.getName());
				innerlifeElementDev.setText(iC.getProducer());
				innerlifeElementDate.setText(iC.getReleaseDate());
				innerlifeElementDesc.setText(iC.getDescription());
			}
		});
	}
	
	private void setInner(IGeometry ig, float scale, Vector3d v, int cosid) {
		
		//inner1 = metaioSDK.createGeometryFromImage(innerfile1);
		if (ig != null)
		{
			ig.setScale(scale);
			ig.setRotation(new Rotation(0f, (float) -Math.PI/4, 0f)); //(float) Math.PI/2
			ig.setTranslation(v);
			//movie.setTransparency(0.1f);
			ig.setCoordinateSystemID(cosid);
			
			//MetaioDebug.log("Loaded geometry "+innerfile1);
		}
		else {
			MetaioDebug.log(Log.ERROR, "Error loading geometry: ");//+innerfile1);
		}
	}
	
	private void unloadInners() {
		
	}
	
	private Computer getComputer(int id) {
		return data.getComputer(id);
	}
		
	
	private void showComputerTimeline(Computer temp) {
		
		final Computer c = temp;
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				layout.setVisibility(View.VISIBLE);
				name.setText(c.getName());
				comp.setText(c.getProducer());
				datebefore.setText("1800");
				date.setText(c.getReleaseDate());
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
	
	private void showComponentView(Component temp) {
		
		final Component c = temp;
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				componentInfo.setVisibility(View.VISIBLE);
				
				componentElementName.setText(c.getName());
				componentElementDev.setText(c.getProducer());
				componentElementDate.setText(c.getReleaseDate());
				componentElementDesc.setText(c.getDescription());
			}
		});
		
	}
	
	private void hideComponentView() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				componentInfo.setVisibility(View.GONE);
			}
		});
		
	}
	
	/*
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
