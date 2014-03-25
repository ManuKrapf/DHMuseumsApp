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
import android.widget.Toast;

import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.*;
import com.metaio.sdk.jni.ETRACKING_STATE;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.Rotation;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;

import dh.computermuseum.Computer.CompVideo;

public class ScanActivity extends ARViewActivity {
	
	Context context = this;
	private Data data;
	
	private int id = 0;
	private int actinner = 0;
	private int[] innerIds = null;
	
	private boolean beforeactive = false;
	private boolean afteractive = false;
	private boolean findcomputer = false;
	
	// Timeline View
	private RelativeLayout timeline;
	private ImageView detailsButton;
	private ImageView videoButton;
	private ImageView innerButton;
	private TextView timelineName;
	private TextView timelineComp;
	private TextView timelineDatebefore;
	private TextView timelineDate;
	private TextView timelineDateafter;
	
	private RelativeLayout timelineOtherView;
	private TextView timelineTagOther;
	private TextView timelineNameOther;
	private ImageView timelineImgOther;
	private ImageView timelineFindOther;
	private ImageView timelineCloseOther;
	
	// Storages View
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
	
	// Innerlife View
	private LinearLayout innerlifeInfo;
	private ImageView closeInnerlife;
	
	private TextView innerlifeElementName;
	private TextView innerlifeElementDev;
	private TextView innerlifeElementDate;
	private TextView innerlifeElementDesc;
	
	// Component View
	private LinearLayout componentInfo;
	
	private TextView componentElementName;
	private TextView componentElementDev;
	private TextView componentElementDate;
	private TextView componentElementDesc;
	
	// Geometrys
	private IGeometry movie;
	private IGeometry g_tag1;
	private IGeometry g_tag2;
	private IGeometry g_tag3;
	private IGeometry g_tag4;
	private IGeometry inner1;
	private IGeometry inner2;
	private IGeometry inner3;
	private IGeometry inner4;
	
	private MetaioSDKCallbackHandler mCallbackHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		data = new Data(this);
		mCallbackHandler = new MetaioSDKCallbackHandler();
		
		//hideAllViews();
	}
	
	private void hideAllViews() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				timeline.setVisibility(View.GONE);
				timelineOtherView.setVisibility(View.GONE);
				storageline.setVisibility(View.GONE);
				innerlifeInfo.setVisibility(View.GONE);
				componentInfo.setVisibility(View.GONE);
			}
		});
		
	}
	
	// TODO LifeCycle Methoden ausbauen das kein unnötiger speicher verbraucht wird

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
			actinner = innerIds[0];
			showInnerlifeComponents(id, actinner);
		}
		else if(geometry.equals(inner2)) {
			Log.d("dhdebug", "geometry is inner 2");
			setInner(inner2, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner3, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner4, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner1, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = innerIds[1];
			showInnerlifeComponents(id, actinner);
		}
		else if(geometry.equals(inner3)) {
			Log.d("dhdebug", "geometry is inner 3");
			setInner(inner3, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner4, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner1, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner2, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = innerIds[2];
			showInnerlifeComponents(id, actinner);
		}
		else if(geometry.equals(inner4)) {
			Log.d("dhdebug", "geometry is inner 4");
			setInner(inner4, 1.3f, new Vector3d(0,0,0), geometry.getCoordinateSystemID());
			setInner(inner1, 0.8f, new Vector3d(-200,0,-20), geometry.getCoordinateSystemID());
			setInner(inner2, 0.5f, new Vector3d(0,0,-100), geometry.getCoordinateSystemID());
			setInner(inner3, 0.8f, new Vector3d(200,0,-20), geometry.getCoordinateSystemID());
			actinner = innerIds[3];
			showInnerlifeComponents(id, actinner);
		}
		else if(geometry.equals(g_tag1)) {
			Log.d("dhdebug", "geometry is g_tag1");
			showComponentView(data.getComponent(5));
			id = 5;
		}
		else if(geometry.equals(g_tag2)) {
			Log.d("dhdebug", "geometry is g_tag2");
			showComponentView(data.getComponent(6));
			id = 6;
		}
		else if(geometry.equals(g_tag3)) {
			Log.d("dhdebug", "geometry is g_tag3");
			showComponentView(data.getComponent(5));
			id = 5;
		}
		else if(geometry.equals(g_tag4)) {
			Log.d("dhdebug", "geometry is g_tag4");
			showComponentView(data.getComponent(6));
			id = 6;
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
					
					timeline = (RelativeLayout) findViewById(R.id.timelineview2);
					
					detailsButton = (ImageView) findViewById(R.id.showDetailsButton2);
					videoButton = (ImageView) findViewById(R.id.showVideoButton);
					innerButton = (ImageView) findViewById(R.id.showInnerlifeButton);
					
					detailsButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							Intent i = new Intent(ScanActivity.this, DetailActivity.class);
							i.putExtra("id", id);
						    startActivity(i);
							
						}
					});
					
					videoButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							movie.setVisible(true);
							movie.startMovieTexture(true);
						}
					});
					
					innerButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							hideComputerTimeline();
							movie.setVisible(false);
							showInnerlifeComponents(id, 18);
						}
					});
					
					// Tag with Timeline
					timelineName = (TextView) findViewById(R.id.tl_nametag);
					timelineComp = (TextView) findViewById(R.id.tl_companytag);
					timelineDatebefore = (TextView) findViewById(R.id.tl_datetagbefore);
					timelineDate = (TextView) findViewById(R.id.tl_datetag2);
					timelineDateafter = (TextView) findViewById(R.id.tl_datetagafter);
					
					// TODO Bei Click auf leeres Element nicht ausblenden
					timelineDatebefore.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(timelineOtherView.getVisibility() == View.GONE || afteractive) {
								showOtherView(id-1, "Früher: ");
								beforeactive = true;
								afteractive = false;
							}
							else {
								hideOtherView();
								beforeactive = false;
							}
						}
					});
					
					timelineDateafter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(timelineOtherView.getVisibility() == View.GONE || beforeactive) {
								showOtherView(id+1, "Später: ");
								afteractive = true;
								beforeactive = false;
							}
							else {
								hideOtherView();
								afteractive = false;
							}
						}
					});
					
					timelineOtherView = (RelativeLayout) findViewById(R.id.timelineshowother);
					timelineTagOther = (TextView) findViewById(R.id.tlother_tag);
					timelineNameOther = (TextView) findViewById(R.id.tlother_name);
					timelineImgOther = (ImageView) findViewById(R.id.tlother_img);
					timelineFindOther = (ImageView) findViewById(R.id.tlother_find);
					timelineCloseOther = (ImageView) findViewById(R.id.tlother_close2);
					
					timelineFindOther.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							hideComputerTimeline();
							
							findcomputer = true;
							CharSequence text = "Suche das eingeblendete Ausstellungsstück!";

							Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
							toast.show();
							
						}
					});
					
					timelineCloseOther.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							hideOtherView();
							findcomputer = false;
						}
					});
					
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
					componentElementDev = (TextView) findViewById(R.id.actual_dev_component);
					componentElementDate = (TextView) findViewById(R.id.actual_date_component);
					componentElementDesc = (TextView) findViewById(R.id.actual_desc_component);
					
					closeInnerlife.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							innerlifeInfo.setVisibility(View.GONE);
							inner1.setVisible(false);
							inner2.setVisible(false);
							inner3.setVisible(false);
							inner4.setVisible(false);
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
			
			timeline.setVisibility(View.GONE);
			timelineOtherView.setVisibility(View.GONE);
			
		}
		
		@Override
		public void onTrackingEvent(TrackingValuesVector values) {
			
			super.onTrackingEvent(values);
			
			if (values.size() > 0) {
				// TODO Tracking Objekte noch austauschen
				
				if(values.get(0).getState() == ETRACKING_STATE.ETS_FOUND) {
					Log.d("dhdebug", values.get(0).getCosName()+" is found");
					
					if(values.get(0).getCosName().equals("overhead_1")) {
						Log.d("dhdebug", "CosID overhead: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("book_2")) {
						Log.d("dhdebug", "CosID book: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("yellow_book_3")) {
						Log.d("dhdebug", "CosID yellowbook: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
						
						// Case 1
						/*
						showComputerTimeline(data.getComputer(1));
						loadMovie(values.get(0).getCoordinateSystemID());
						initInnerlife(data.getComputer(1), values.get(0).getCoordinateSystemID());
						*/
						
						// Case 2
						//showStorageLine(11);
						
						// Case 3
						//showMBTags(values.get(0).getCoordinateSystemID());
						
					}
				}
				else if(values.get(0).getState() == ETRACKING_STATE.ETS_LOST) {
					id = 0;
					// TODO überprüfen was alles entfernt werden muss wenn objekt nicht mehr getrackt wird
					metaioSDK.unloadGeometry(movie);
					hideComputerTimeline();
					hideComponentView(); // TODO soll das wirklich ausgeblendet werden???
					if(!findcomputer) {
						hideOtherView();
					}
				}
				else {
					Log.d("dhdebug", "nothing is registered or found");
				}
				
			}
			
		}
	}
	
	private void showCase(int ocase, int cosid) {
		
		switch(ocase) {
			case 1: hideOtherView();
				findcomputer = false;
				showComputerTimeline(data.getComputer(id));
				innerIds = data.getInnerlifeComponentsIDs(id);
				loadMovie(cosid);
				initInnerlife(data.getComputer(id), cosid);
				break;
			case 2: showStorageLine(id);
				break;
			case 3: showMBTags(cosid);
				break;
			default:
		}
		
	}
	
	// Case 1: Timeline for computers
	
	private void showComputerTimeline(Computer temp) {
		
		final Computer cbefore = data.getComputer(id-1);
 		final Computer c = temp;
 		final Computer cafter = data.getComputer(id+1);
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				// TODO Date für Before and After dynamisch setzen
				timeline.setVisibility(View.VISIBLE);
				timelineName.setText(c.getName());
				timelineComp.setText(c.getProducer());
				timelineDate.setText(c.getReleaseDate());
				
				if(cbefore != null) {
					timelineDatebefore.setText(cbefore.getReleaseDate());
				}
				else {
					timelineDatebefore.setText("");
				}
				
				if(cafter != null) {
					timelineDateafter.setText(cafter.getReleaseDate());
				}
				else {
					timelineDateafter.setText("");
				}
			}
		});
		
	}
	
	private void hideComputerTimeline() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				timeline.setVisibility(View.GONE);
			}
		});
		
	}
	
	private void showOtherView(int id, String s) {
		
		final Computer c = data.getComputer(id);
		final String str = s;
		
		if(c != null) {
			
			runOnUiThread(new Runnable() 
			{
				@Override
				public void run() 
				{
			
					timelineOtherView.setVisibility(View.VISIBLE);
					
					timelineTagOther.setText(str);
					timelineNameOther.setText(c.getName());
					
					Resources res = getResources();
					String tempImgName = c.getImg();
					int tempResId = res.getIdentifier(tempImgName, "drawable", getPackageName());
					Drawable tempImg = res.getDrawable(tempResId);
					timelineImgOther.setImageDrawable(tempImg);
					
				}
			});
		}
		
	}
	
	private void hideOtherView() {
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				timelineOtherView.setVisibility(View.GONE);
			}
		});
	}
	
	// Case 1.1: show a movie on the screen of the computer
	
	private void loadMovie(int cosid) {
		// TODO Position des Videos noch besser anpassen
		
		CompVideo video = data.getComputer(id).getVideo();
		float[] pos = video.getPos();
		
		final String moviePath = AssetsManager.getAssetPath("Assets/"+video.getPath()); //videoc128.3g2");
		
		if (moviePath != null)
		{
			movie = metaioSDK.createGeometryFromMovie(moviePath, false);
			if (movie != null)
			{
				movie.setScale(1.5f);
				movie.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
				movie.setTransparency(0.1f);
				movie.setTranslation(new Vector3d(pos[0],pos[1],pos[2]));
				movie.setCoordinateSystemID(cosid);
				//movie.startMovieTexture(true);
				movie.setVisible(false);
				
				MetaioDebug.log("Loaded geometry "+moviePath);
			}
			else {
				MetaioDebug.log(Log.ERROR, "Error loading geometry: "+moviePath);
			}
		}
		
	}
	
	// Case 1.2: show the innerlife components of the computer
	
	private void initInnerlife(Computer c, int cosid) {
		
		final String innerfile1 = AssetsManager.getAssetPath("Assets/"+c.getComponent(1).getImg());
		final String innerfile2 = AssetsManager.getAssetPath("Assets/"+c.getComponent(2).getImg());
		final String innerfile3 = AssetsManager.getAssetPath("Assets/"+c.getComponent(3).getImg());
		final String innerfile4 = AssetsManager.getAssetPath("Assets/"+c.getComponent(4).getImg());
		
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
			actinner = innerIds[0];
		}
		
		//showInnerlifeComponents(id, actinner);
	}
	
	private void setInner(IGeometry ig, float scale, Vector3d v, int cosid) {
		
		if (ig != null)
		{
			ig.setVisible(false);
			ig.setScale(scale);
			ig.setRotation(new Rotation(0f, (float) -Math.PI/4, 0f)); //(float) Math.PI/2
			ig.setTranslation(v);
			ig.setCoordinateSystemID(cosid);
			
			//MetaioDebug.log("Loaded geometry "+innerfile1);
		}
		else {
			MetaioDebug.log(Log.ERROR, "Error loading geometry: ");//+innerfile1);
		}
	}
	
	private void showInnerlifeComponents(int parentid, int id) {
		
		final InnerlifeComponent iC = data.getInnerlifeComponent(parentid, id);
		
		inner1.setVisible(true);
		inner2.setVisible(true);
		inner3.setVisible(true);
		inner4.setVisible(true);
		
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
	
	// Case 2: Storages in chronological order
	
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
	
	// Case 3: Tags for components of a board
	
	private void showMBTags(int cosid) {
		// TODO setze Bilder für Tags dynamisch
		
		final String tag1 = AssetsManager.getAssetPath("Assets/mbtag_southbridge.png");
		final String tag2 = AssetsManager.getAssetPath("Assets/mbtag_northbridge.png");
		final String tag3 = AssetsManager.getAssetPath("Assets/mbtag_ram.png");
		final String tag4 = AssetsManager.getAssetPath("Assets/mbtag_cpu.png");
		
		if (tag1 != null && g_tag1 == null)
		{
			g_tag1 = metaioSDK.createGeometryFromImage(tag1, true, true);
			setMBTag(g_tag1, new Vector3d(100,0,2), cosid);
		}
		
		if (tag2 != null && g_tag2 == null)
		{
			g_tag2 = metaioSDK.createGeometryFromImage(tag2, true, true);
			setMBTag(g_tag2, new Vector3d(150,0,10), cosid);
		}
		
		if (tag3 != null && g_tag3 == null)
		{
			g_tag3 = metaioSDK.createGeometryFromImage(tag3, true, true);
			setMBTag(g_tag3, new Vector3d(0,0,0), cosid);
		}
		
		if (tag4 != null  && g_tag4 == null)
		{
			g_tag4 = metaioSDK.createGeometryFromImage(tag4, true, true);
			setMBTag(g_tag4, new Vector3d(-120,0,-20), cosid);
		}
		
	}
	
	private void setMBTag(IGeometry ig, Vector3d v, int cosid) {
		
		if (ig != null)
		{
			//movie.setScale(1.5f);
			ig.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
			ig.setTranslation(v);
			ig.setCoordinateSystemID(cosid);
			
			MetaioDebug.log("Loaded geometry ");
		}
		else {
			MetaioDebug.log(Log.ERROR, "Error loading geometry: ");
		}
		
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
	
}
