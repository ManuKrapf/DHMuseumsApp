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

import dh.computermuseum.Component.Tag;
import dh.computermuseum.Computer.CompVideo;

public class ScanActivity extends ARViewActivity {
	
	Context context = this;
	private Data data;
	
	private int id = 0;
	private int actinner = 0;
	private int[] innerIds = null;
	private int tagid = 0;
	private int storageid = 0;
	private int actCase = 0;
	
	private boolean beforeActive = false;
	private boolean beforeEmpty = false;
	private boolean afterActive = false;
	private boolean afterEmpty = false;
	private boolean findcomputer = false;
	
	// Timeline View
	private RelativeLayout timelinecontainer;
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
	private LinearLayout componentView;
	//private LinearLayout componentInfo;
	private LinearLayout componentDetailInfo;
	
	private TextView componentName;
	private TextView componentProducer;
	private ImageView componentDetailButton;
	
	private TextView componentElementName;
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
	
	float xrot = 1.8f;
	float yrot = -0.5f;
	float zrot = 1.1f;
	float xtrans = -100f;
	float ytrans = 0f;
	float ztrans = 0f;
	
	private MetaioSDKCallbackHandler mCallbackHandler;
	
	/**
	 * Called on Activity start
	 */
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
	/**
	 * MetaioSDK Method
	 * returns the android Layout
	 */
	@Override
	protected int getGUILayout() {
		return R.layout.scan;
	}
	
	/**
	 * MetaioSDK Method
	 * returns the MetaioSDKCallback
	 */
	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		return mCallbackHandler;
	}
	
	/**
	 * MetaioSDK Method
	 * Load the needed content for tracking with the MetaioSDK
	 */
	@Override
	protected void loadContents() {
		try
		{
			AssetsManager.extractAllAssets(getApplicationContext(), BuildConfig.DEBUG);
			
			// Getting a file path for tracking configuration XML file
			String trackingConfigFile = AssetsManager.getAssetPath("trackingdata/Tracking.xml");
			
			// Assigning tracking configuration
			boolean result = metaioSDK.setTrackingConfiguration(trackingConfigFile);
			
			MetaioDebug.log("Tracking data loaded: " + result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// TODO check for try catch setting
	
	/**
	 * MetaioSDK Method
	 * CallbackHandler for clicks on a Geometry
	 */
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
			tagid = 1;
			showComponentTagView();
		}
		else if(geometry.equals(g_tag2)) {
			Log.d("dhdebug", "geometry is g_tag2");
			tagid = 2;
			showComponentTagView();
		}
		else if(geometry.equals(g_tag3)) {
			Log.d("dhdebug", "geometry is g_tag3");
			tagid = 3;
			showComponentTagView();
		}
		else if(geometry.equals(g_tag4)) {
			Log.d("dhdebug", "geometry is g_tag4");
			tagid = 4;
			showComponentTagView();
		}
		else if(geometry.equals(movie)) {
			
			//xrot += 0.1;
			//yrot -= 0.1;
			zrot += 0.1;
			Log.d("dhdebug", "Rotation: x="+xrot+", y="+yrot+", z="+zrot);
			//geometry.setRotation(new Rotation(xrot, yrot, zrot));
			
			//xtrans += 10; // Auf Handy zu
			//ytrans += 10; // nach recht und links
			//ztrans += 10; // oben und unten
			Log.d("dhdebug", "Translation: x="+xtrans+", y="+ytrans+", z="+ztrans);
			geometry.setTranslation(new Vector3d(xtrans, ytrans, ztrans));
			
		}
		
	}
	
	/**
	 * MetaioSDK Class
	 * 
	 * Class for Metaio CallbackHandler
	 */
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback 
	{
		/**
		 * MetaioSDK Method
		 * 
		 * called when SDK is loaded
		 * used to load all used UI content
		 */
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
					
					timelinecontainer = (RelativeLayout) findViewById(R.id.timelineviewcontainer);
					timeline = (RelativeLayout) findViewById(R.id.timelineview2);
					
					detailsButton = (ImageView) findViewById(R.id.showDetailsButton2);
					videoButton = (ImageView) findViewById(R.id.showVideoButton);
					innerButton = (ImageView) findViewById(R.id.showInnerlifeButton);
					
					detailsButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							Intent i = new Intent(ScanActivity.this, DetailActivity.class);
							i.putExtra("id", id);
							i.putExtra("type", "computer");
						    startActivity(i);
							
						}
					});
					
					videoButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(movie.isVisible()) {
								movie.setVisible(false);
								movie.startMovieTexture(false);
							}
							else {
								movie.setVisible(true);
								movie.startMovieTexture(true);
							}
						}
					});
					
					innerButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							hideComputerTimeline();
							hideTimelineContainer();
							if(movie != null) {
								movie.setVisible(false);
							}
							showInnerlifeComponents(id, actinner);
						}
					});
					
					// Tag with Timeline
					timelineName = (TextView) findViewById(R.id.tl_nametag);
					timelineComp = (TextView) findViewById(R.id.tl_companytag);
					timelineDatebefore = (TextView) findViewById(R.id.tl_datetagbefore);
					timelineDate = (TextView) findViewById(R.id.tl_datetag2);
					timelineDateafter = (TextView) findViewById(R.id.tl_datetagafter);
					
					timelineDatebefore.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(timelineOtherView.getVisibility() == View.GONE || afterActive) {
								if(!beforeEmpty) {
									showOtherView(id-1, "Früher: ");
									beforeActive = true;
									afterActive = false;
								}
							}
							else {
								if(!beforeEmpty) {
									hideOtherView();
									beforeActive = false;
								}
							}
						}
					});
					
					timelineDateafter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							if(timelineOtherView.getVisibility() == View.GONE || beforeActive) {
								if(!afterEmpty) {
									showOtherView(id+1, "Später: ");
									afterActive = true;
									beforeActive = false;
								}
							}
							else {
								if(!afterEmpty) {
									hideOtherView();
									afterActive = false;
								}
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
					
					closesl.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							hideStorageLine();
							storageid = 0;
						}
					});
					
					gobefore.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							storageid = storageid-1;
							showStorageLine();
							
							// animation fading
							gobefore.startAnimation(fadeOutAnim);
							if(storageid > 7) {
								gobefore.startAnimation(fadeInAnim);
							}
							
						}
					});
					
					goafter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
							storageid = storageid+1;
							showStorageLine();
							
							// animation fading
							goafter.startAnimation(fadeOutAnim);
							if(storageid < 17) {
								goafter.startAnimation(fadeInAnim);
							}
						}
					});
					
					// Innerlife information view
					innerlifeInfo = (LinearLayout) findViewById(R.id.innerlifeOverview);
					innerlifeElementName = (TextView) findViewById(R.id.actual_name_innerlife);
					innerlifeElementDev = (TextView) findViewById(R.id.actual_dev_innerlife);
					innerlifeElementDate = (TextView) findViewById(R.id.actual_date_innerlife);
					innerlifeElementDesc = (TextView) findViewById(R.id.actual_desc_innerlife);
					closeInnerlife = (ImageView) findViewById(R.id.close_innerlife);
					
					closeInnerlife.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							hideInnerlifeComponents();
							if(id != 0) {
								showComputerTimeline();
							}
						}
					});
					
					// Component information view
					
					componentView = (LinearLayout) findViewById(R.id.componentView);
					//componentInfo = (LinearLayout) findViewById(R.id.componentOverview);
					componentDetailInfo = (LinearLayout) findViewById(R.id.componentDetailView);
					
					componentName = (TextView) findViewById(R.id.component_name);
					componentProducer = (TextView) findViewById(R.id.component_producer);
					
					componentElementName = (TextView) findViewById(R.id.actual_name_component);
					componentElementDesc = (TextView) findViewById(R.id.actual_desc_component);
					
					componentDetailButton = (ImageView) findViewById(R.id.showDetailsButtonComponent);
					
					componentDetailButton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Intent i = new Intent(ScanActivity.this, DetailActivity.class);
							i.putExtra("id", id);
							i.putExtra("type", "component");
						    startActivity(i);
						}
					});
					
				}
			});
			
		}
		
		/**
		 * MetaioSDK Method
		 * 
		 * called if the tracking state changes
		 */
		@Override
		public void onTrackingEvent(TrackingValuesVector values) {
			
			super.onTrackingEvent(values);
			
			if (values.size() > 0) {
				// TODO Tracking Objekte noch austauschen
				
				/**
				 * An Object is tracked
				 */
				if(values.get(0).getState() == ETRACKING_STATE.ETS_FOUND) {
					Log.d("dhdebug", values.get(0).getCosName()+" is found");
					
					if(values.get(0).getCosName().equals("commodorecbm4032_1")) {
						Log.d("dhdebug", "CosID cbm4032: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("toshibat3200sx_2")) {
						Log.d("dhdebug", "CosID toshiba: "+values.get(0).getCoordinateSystemID());
						id = 2;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("ibml40_3")) {
						Log.d("dhdebug", "CosID ibm: "+values.get(0).getCoordinateSystemID());
						Rotation rot = values.get(0).getRotation();
						Vector3d vrot1 = rot.getEulerAngleDegrees();
						Vector3d vrot2 = rot.getEulerAngleRadians();
						Vector3d trans = values.get(0).getTranslation();
						Log.d("dhdebug", "ibm Rotation1: x="+vrot1.getX()+", y="+vrot1.getY()+", z="+vrot1.getZ());
						Log.d("dhdebug", "ibm Rotation2: x="+vrot2.getX()+", y="+vrot2.getY()+", z="+vrot2.getZ());
						Log.d("dhdebug", "ibm Translation: x="+trans.getX()+", y="+trans.getY()+", z="+trans.getZ());
						/*xrot = (vrot.getX());
						yrot = (vrot.getY());
						zrot = (vrot.getZ());
						xtrans = (trans.getX());
						ytrans = (trans.getY());
						ztrans = (trans.getZ());*/
						id = 3;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("Diskette_4")) {
						Log.d("dhdebug", "CosID overhead: "+values.get(0).getCoordinateSystemID());
						id = 11;
						showCase(2, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("platine1-80386_5")) {
						Log.d("dhdebug", "CosID overhead: "+values.get(0).getCoordinateSystemID());
						id = 5;
						showCase(3, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("platine2_80586_6")) {
						Log.d("dhdebug", "CosID overhead: "+values.get(0).getCoordinateSystemID());
						id = 5;
						showCase(3, values.get(0).getCoordinateSystemID());
					}
					
					// Test Objekte
					else if(values.get(0).getCosName().equals("overhead_7")) {
						Log.d("dhdebug", "CosID overhead: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("book_8")) {
						Log.d("dhdebug", "CosID book: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("yellow_book_9")) {
						Log.d("dhdebug", "CosID yellowbook: "+values.get(0).getCoordinateSystemID());
						id = 1;
						showCase(1, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("mensacard_10")) {
						Log.d("dhdebug", "CosID mensacard: "+values.get(0).getCoordinateSystemID());
						id = 11;
						showCase(2, values.get(0).getCoordinateSystemID());
					}
					else if(values.get(0).getCosName().equals("haselnussschnitte_11")) {
						Log.d("dhdebug", "CosID hanuta: "+values.get(0).getCoordinateSystemID());
						id = 5;
						showCase(3, values.get(0).getCoordinateSystemID());
					}
					
				}
				else if(values.get(0).getState() == ETRACKING_STATE.ETS_LOST) {
					id = 0;
					Log.d("dhdebug", values.get(0).getCosName()+" is lost");
					unloadWhenLost();
				}
				else {
					Log.d("dhdebug", "nothing is registered or found");
				}
				
			}
			
		}
	}
	
	/**
	 * Show one of the three cases for the tracked object
	 * 
	 * @param int ocase the ID of the case to load
	 * @param int cosid the CoordinateSystemID of the tracked object
	 */
	private void showCase(int ocase, int cosid) {
		
		actCase = ocase;
		
		switch(ocase) {
			case 1: unloadForCase(1);
				hideOtherView();
				findcomputer = false;
				showComputerTimeline();
				innerIds = data.getInnerlifeComponentsIDs(id);
				initInnerlife(cosid);
				loadMovie(cosid);
				break;
			case 2: unloadForCase(2);
				showStorageLine();
				break;
			case 3: 
				unloadForCase(3);
				showBoardTags(cosid);
				showBoardInfo();
				break;
			default:
		}
		
	}
	
	/**
	 * Unload the other cases when a new case is loaded
	 * 
	 * @param int ocase the case for that the other cases must be unloaded, eg. if ocase = 1 case 2 and 3 will be unloaded 
	 */
	private void unloadForCase(int ocase) {
		
		switch(ocase) {
			// Unload Case 2 und 3
			case 1: hideStorageLine();
				unloadBoardTags();
				hideBoardInfo();
				hideComponentTagView();
				break;
			// Unload Case 1 und 3
			case 2: unloadMovie();
				unloadInnerLife();
				hideTimelineContainer();
				unloadBoardTags();
				hideBoardInfo();
				hideComponentTagView();
				break;
			// Unload Case 1 und 2
			case 3: unloadMovie();
				unloadInnerLife();
				hideTimelineContainer();
				hideStorageLine();
				break;
			default: //unloadAllCases();
		}
		
	}
	
	/**
	 * unloads the actual case that is shown when the tracking object is lost
	 */
	private void unloadWhenLost() {
		
		switch(actCase) {
			case 1: unloadMovie();
				unloadInnerLife();
				hideComputerTimeline();
				if(!findcomputer){
					hideOtherView();
					hideTimelineContainer();
				}
				break;
			case 2: //hideStorageLine();
				break;
			case 3: unloadBoardTags();
				hideBoardInfo();
				hideComponentTagView();
				break;
			default: //unloadAllCases();
		}
		
	}
	
	/**
	 * unload all cases
	 */
	private void unloadAllCases() {
		
		// Unload Case 1
		unloadMovie();
		unloadInnerLife();
		hideTimelineContainer();
		hideComputerTimeline();
		hideOtherView();
		
		// Unload Case 2
		hideStorageLine();
		
		// Unload Case 3
		unloadBoardTags();
		hideBoardInfo();
		hideComponentTagView();
		
	}
	
	/** 
	 * Case 1
	 * Timeline for computers
	 * 
	 * Shows a timeline for a computer with general infos to the computer and the ancestor and
	 * the successor in the timeline. Furthermore it provides 3 Buttons to get a detailscreen and
	 * go to the 2 subcases, showing a video or showing the interior
	 */
	
	/**
	 * Shows the Timeline for the computer when the object is trakced
	 */
	private void showComputerTimeline() {
		
		final Computer cbefore = data.getComputer(id-1);
 		final Computer c = data.getComputer(id);
 		final Computer cafter = data.getComputer(id+1);
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run()
			{
				timelinecontainer.setVisibility(View.VISIBLE);
				timeline.setVisibility(View.VISIBLE);
				timelineName.setText(c.getName());
				timelineComp.setText(c.getProducer());
				timelineDate.setText(c.getReleaseDate());
				
				if(cbefore != null) {
					timelineDatebefore.setText(cbefore.getReleaseDate());
					beforeEmpty = false;
				}
				else {
					timelineDatebefore.setText("");
					beforeEmpty = true;
				}
				
				if(cafter != null) {
					timelineDateafter.setText(cafter.getReleaseDate());
					afterEmpty = false;
				}
				else {
					timelineDateafter.setText("");
					afterEmpty = true;
				}
			}
		});
		
	}
	
	/**
	 * Hides the Timeline when the computer is lost
	 */
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
	
	/**
	 * Hides the container View in which all Timeline UI Elements are
	 */
	private void hideTimelineContainer() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run()
			{
				timelinecontainer.setVisibility(View.GONE);
			}
		});
		
	}
	
	/**
	 * Shows the View with a picture for a ancestor or successor in the timeline
	 * to find this object
	 * 
	 * @param int id ID of the Computer shown in the View
	 * @param String s Tag before the name, used Values are "Früher: " or "Später: "
	 */
	private void showOtherView(int id, String s) {
		
		final Computer c = data.getComputer(id);
		final String str = s;
		
		if(c != null) {
			
			runOnUiThread(new Runnable() 
			{
				@Override
				public void run() 
				{
					
					timelinecontainer.setVisibility(View.VISIBLE);
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
	
	/**
	 * Hides the View for ancestor or successor
	 */
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
	
	/** 
	 * Case 1.1
	 * Shows a movie on the screen of the computer
	 * 
	 * 
	 */
	
	/**
	 * loads a video, eg. to show an operating system on the screen
	 * 
	 * @param int cosid the CoordinateSystemID of the tracked object
	 */
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
				movie.setRotation(new Rotation(1.8f, -0.4f, 1.1f)); // (float) Math.PI/2 , 1.8f, -0.4f, 1.1f
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
	
	/**
	 * unloads the movie when it is not used anymore
	 */
	private void unloadMovie() {
		
		if(movie != null) {
			metaioSDK.unloadGeometry(movie);
			movie = null;
		}
		
	}
	
	/**
	 * Case 1.2
	 * show the interior components of the computer
	 */
	
	/**
	 * Inits the interior Geometrys of the computer
	 * 
	 * @param int cosid the CoordinateSystemID of the tracked object
	 */
	private void initInnerlife(int cosid) {
		
		Computer c = data.getComputer(id);
		
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
		
	}
	
	/**
	 * unloads the interior objects when unused
	 */
	private void unloadInnerLife() {
		
		if(inner1 != null) {
			metaioSDK.unloadGeometry(inner1);
			inner1 = null;
		}
		
		if(inner2 != null) {
			metaioSDK.unloadGeometry(inner2);
			inner2 = null;
		}
		
		if(inner3 != null) {
			metaioSDK.unloadGeometry(inner3);
			inner3 = null;
		}
		
		if(inner4 != null) {
			metaioSDK.unloadGeometry(inner4);
			inner4 = null;
		}
		
	}
	
	/**
	 * Edits a single Geometry for an interior item
	 * 
	 * @param IGeometry ig the geometry to edit
	 * @param float scale a value to scale the object
	 * @param Vector3d v a 3D vector to move the object
	 * @param int cosid the CoordinateSystemID of the tracked object
	 */
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
	
	/**
	 * Shows a View with infos to the interior object
	 * 
	 * @param int parentid the ID of the parent computer
	 * @param int id the ID of the interior object
	 */
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
	
	/**
	 * Hides the iterior objects
	 */
	private void hideInnerlifeComponents() {
		//TODO abfangen wenn element is lost ...
		if(inner1 != null) {
			inner1.setVisible(false);
		}
		if(inner2 != null) {
			inner2.setVisible(false);
		}
		if(inner3 != null) {
			inner3.setVisible(false);
		}
		if(inner4 != null) {
			inner4.setVisible(false);
		}
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				innerlifeInfo.setVisibility(View.GONE);
			}
		});
		
	}
	
	/** 
	 * Case 2
	 * Storages in chronological order
	 * 
	 * Shows a View for a set of storage objects in a chronological order. Once can go through the whole
	 * set by getting to the ancesctors or successors of the tracked object of the set.
	 * Stays also visible if the tracked object is lost.
	 */
	
	/**
	 * Shows the View for the storage set
	 */
	private void showStorageLine() {
		
		try {
			
			if(storageid == 0) {
				storageid = id;
			}
			
			Storage before = null;
			if(storageid > 7) {
				before = data.getStorage(storageid-1);
			}
			
			Storage after = null;
			if(storageid < 17) {
				after = data.getStorage(storageid+1);
			}
			
			final Storage sbefore = before;
			final Storage s = data.getStorage(storageid);
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
		catch(Exception e) {
			Log.e("dhdebug", "Fehler beim Anzeigen der Storageline!");
			Log.e("dhdebug", "Fehler: "+e.getMessage());
		}
		
	}
	
	/**
	 * Hides the View if the user close it
	 */
	private void hideStorageLine() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				storageline.setVisibility(View.GONE);
			}
		});
		
	}
	
	/**
	 * Case 3
	 * Infos and component-tags for a board
	 * 
	 * Shows a View with general Infos to the board and tags which locates interesting compontents
	 * of a board. By clicking the tags it provides further informations to the tags.
	 */
	
	/**
	 * Init and show the Geometry for the tags
	 * 
	 * @param cosid
	 */
	private void showBoardTags(int cosid) {
		
		ArrayList<Tag> tags = data.getTags(id);
		float [] pos = null;
		
		final String tag1 = AssetsManager.getAssetPath("Assets/"+tags.get(0).getImg());
		final String tag2 = AssetsManager.getAssetPath("Assets/"+tags.get(1).getImg());
		final String tag3 = AssetsManager.getAssetPath("Assets/"+tags.get(2).getImg());
		final String tag4 = AssetsManager.getAssetPath("Assets/"+tags.get(3).getImg());
		
		if (tag1 != null && g_tag1 == null)
		{
			pos = tags.get(0).getPos();
			g_tag1 = metaioSDK.createGeometryFromImage(tag1, true, true);
			setBoardTag(g_tag1, new Vector3d(pos[0],pos[1],pos[2]), cosid); // new Vector3d(100,0,2)
		}
		
		if (tag2 != null && g_tag2 == null)
		{
			pos = tags.get(1).getPos();
			g_tag2 = metaioSDK.createGeometryFromImage(tag2, true, true);
			setBoardTag(g_tag2, new Vector3d(pos[0],pos[1],pos[2]), cosid); // new Vector3d(150,0,10)
		}
		
		if (tag3 != null && g_tag3 == null)
		{
			pos = tags.get(2).getPos();
			g_tag3 = metaioSDK.createGeometryFromImage(tag3, true, true);
			setBoardTag(g_tag3, new Vector3d(pos[0],pos[1],pos[2]), cosid); // new Vector3d(0,0,0)
		}
		
		if (tag4 != null  && g_tag4 == null)
		{
			pos = tags.get(3).getPos();
			g_tag4 = metaioSDK.createGeometryFromImage(tag4, true, true);
			setBoardTag(g_tag4, new Vector3d(pos[0],pos[1],pos[2]), cosid); // new Vector3d(-120,0,-20)
		}
		
	}
	
	/**
	 * Unload the component-tags for the board
	 */
	private void unloadBoardTags() {
		
		if(g_tag1 != null) {
			metaioSDK.unloadGeometry(g_tag1);
			g_tag1 = null;
		}
		
		if(g_tag2 != null) {
			metaioSDK.unloadGeometry(g_tag2);
			g_tag2 = null;
		}
		
		if(g_tag3 != null) {
			metaioSDK.unloadGeometry(g_tag3);
			g_tag3 = null;
		}
		
		if(g_tag4 != null) {
			metaioSDK.unloadGeometry(g_tag4);
			g_tag4 = null;
		}
		
	}
	
	/**
	 * Edits a Geometry for a board tag
	 * 
	 * @param IGeometry ig the geometry to edit
	 * @param Vector3d v a 3D vector to move the geometry
	 * @param int cosid the CoordinateSystemID of the tracked object
	 */
	private void setBoardTag(IGeometry ig, Vector3d v, int cosid) {
		
		if (ig != null)
		{
			ig.setRotation(new Rotation((float) Math.PI/2, 0f, 0f));
			ig.setTranslation(v);
			ig.setCoordinateSystemID(cosid);
			
			MetaioDebug.log("Loaded geometry ");
		}
		else {
			MetaioDebug.log(Log.ERROR, "Error loading geometry: ");
		}
		
	}
	
	/**
	 * Shows a View with the general board info
	 */
	private void showBoardInfo() {
		
		final Component c = data.getComponent(id);
		
		runOnUiThread(new Runnable()
		{
			@Override
			public void run() 
			{
				componentView.setVisibility(View.VISIBLE);
				
				componentName.setText(c.getName());
				componentProducer.setText(c.getProducer());
				
			}
		});
		
	}
	
	/**
	 * Hides the View with the board infos
	 */
	private void hideBoardInfo() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				componentView.setVisibility(View.GONE);
			}
		});
		
	}
	
	/**
	 * Shows a View with infos to a component-tag
	 */
	private void showComponentTagView() {
		
		final Component c = data.getComponent(id);
		final Tag t = c.getTag(tagid);
		
		runOnUiThread(new Runnable()
		{
			@Override
			public void run() 
			{
				componentDetailInfo.setVisibility(View.VISIBLE);
				
				componentElementName.setText(t.getName());
				componentElementDesc.setText(t.getDesc());
				
			}
		});
		
	}
	
	/**
	 * Hides the View with the infos to a component-tag
	 */
	private void hideComponentTagView() {
		
		runOnUiThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				componentDetailInfo.setVisibility(View.GONE);
			}
		});
		
	}
	
}
