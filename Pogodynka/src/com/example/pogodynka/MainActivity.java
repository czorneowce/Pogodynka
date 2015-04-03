package com.example.pogodynka;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int viewHeight;

	private ImageView kolko;
	private ImageView kolko2;
	private ImageView imidzwiew;

	private LocationManager mLocationManager;

	private TextView cityName;
	
	private double lat, lon;

	private RelativeLayout layoutRano;
	private RelativeLayout layoutPoludnie;
	private RelativeLayout layoutWieczor;
	private RelativeLayout layoutNoc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		layoutRano = (RelativeLayout) findViewById(R.id.layoutRano);
		layoutPoludnie = (RelativeLayout) findViewById(R.id.layoutPoludnie);
		layoutWieczor = (RelativeLayout) findViewById(R.id.layoutWieczor);
		layoutNoc = (RelativeLayout) findViewById(R.id.layoutNoc);

		cityName = (TextView) findViewById(R.id.nazwaMiastaTextView);

		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		viewHeight = size.y - getStatusBarHeight();

		layoutRano.getLayoutParams().height = viewHeight / 3;

		setListeners();

		LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, mLocationListener);
		} else {
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 5, mLocationListener);
		}

		new GetYrno().execute();

		/*kolko = (ImageView) findViewById(R.id.kolko);
		kolko2 = (ImageView) findViewById(R.id.kolko2);
		imidzwiew = (ImageView) findViewById(R.id.imidzwiew);

		final MainActivity that = this;

		kolko.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ObjectAnimator oa = ObjectAnimator.ofFloat(kolko, "scaleX", 1.0f, 15.0f).setDuration(500);
				ObjectAnimator ob = ObjectAnimator.ofFloat(kolko, "scaleY", 1.0f, 15.0f).setDuration(500);
				oa.start();
				ob.start();
				oa.addListener(new AnimatorListener() {

					@Override
					public void onAnimationStart(Animator animation) {
					}

					@Override
					public void onAnimationRepeat(Animator animation) {
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						Intent intent = new Intent(MainActivity.this, DrugieActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						startActivity(intent);	
					}

					@Override
					public void onAnimationCancel(Animator animation) {
					}
				});
				return false;
			}
		});

		kolko2.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				ObjectAnimator oa = ObjectAnimator.ofFloat(kolko2, "scaleX", 1.0f, 15.0f).setDuration(500);
				ObjectAnimator ob = ObjectAnimator.ofFloat(kolko2, "scaleY", 1.0f, 15.0f).setDuration(500);
				oa.start();
				ob.start();
				oa.addListener(new AnimatorListener() {

					@Override
					public void onAnimationStart(Animator animation) {
					}

					@Override
					public void onAnimationRepeat(Animator animation) {
					}

					@Override
					public void onAnimationEnd(Animator animation) {
					}

					@Override
					public void onAnimationCancel(Animator animation) {
					}
				});
				return false;
			}
		});*/
	}

	/*@Override
	protected void onResume() {
		if (Zmienne.powrot == true) {
			ObjectAnimator oa = ObjectAnimator.ofFloat(kolko, "scaleX", 15.0f, 1.0f).setDuration(500);
			ObjectAnimator ob = ObjectAnimator.ofFloat(kolko, "scaleY", 15.0f, 1.0f).setDuration(500);
			oa.start();
			ob.start();
			Zmienne.powrot = false;
		}
		super.onResume();
	}*/

	private final LocationListener mLocationListener = new LocationListener() {
		@Override
		public void onLocationChanged(final Location location) {
			Log.d("XQ", "--------------------------------------------------\nlongitude: " + location.getLongitude() + "\nlatitude: " + location.getLatitude());
			lat = location.getLatitude();
			lon = location.getLongitude();
			Log.d("SJ", lat +"- lat   lon: "+lon);
			Geocoder geo = new Geocoder(MainActivity.this, Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

				if(addresses.size() > 0) {
					cityName.setText(addresses.get(0).getLocality()+", "+addresses.get(0).getCountryName());
					Log.d("XQ",addresses.get(0).getAddressLine(0) +", "
							+ addresses.get(0).getAddressLine(1) +", "
							+ addresses.get(0).getAddressLine(2) +", "
							+ addresses.get(0).getFeatureName() + ", "//to jest chyba numer domu
							+ addresses.get(0).getLocality() +", "
							+ addresses.get(0).getCountryName() + "\n--------------------------------------------------");
				}
			} catch (IOException e) {
				Log.d("XQ", e.getMessage());
				e.printStackTrace();
			}  
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}
	};
	public void setListeners() {
		layoutRano.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation ranoAnim = new HeightAnimation(layoutRano, viewHeight / 3);
				Animation poludnieAnim = new HeightAnimation(layoutPoludnie, 0);
				Animation wieczorAnim = new HeightAnimation(layoutWieczor, 0);
				Animation nocAnim = new HeightAnimation(layoutNoc, 0);

				ranoAnim.setDuration(500);
				poludnieAnim.setDuration(500);
				wieczorAnim.setDuration(500);
				nocAnim.setDuration(500);

				ranoAnim.setInterpolator(new DecelerateInterpolator());
				poludnieAnim.setInterpolator(new DecelerateInterpolator());
				wieczorAnim.setInterpolator(new DecelerateInterpolator());
				nocAnim.setInterpolator(new DecelerateInterpolator());

				layoutRano.startAnimation(ranoAnim);
				layoutPoludnie.startAnimation(poludnieAnim);
				layoutWieczor.startAnimation(wieczorAnim);
				layoutNoc.startAnimation(nocAnim);
				return false;
			}
		});

		layoutPoludnie.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation ranoAnim = new HeightAnimation(layoutRano, 0);
				Animation poludnieAnim = new HeightAnimation(layoutPoludnie, viewHeight / 3);
				Animation wieczorAnim = new HeightAnimation(layoutWieczor, 0);
				Animation nocAnim = new HeightAnimation(layoutNoc, 0);

				ranoAnim.setDuration(500);
				poludnieAnim.setDuration(500);
				wieczorAnim.setDuration(500);
				nocAnim.setDuration(500);


				ranoAnim.setInterpolator(new DecelerateInterpolator());
				poludnieAnim.setInterpolator(new DecelerateInterpolator());
				wieczorAnim.setInterpolator(new DecelerateInterpolator());
				nocAnim.setInterpolator(new DecelerateInterpolator());

				layoutRano.startAnimation(ranoAnim);
				layoutPoludnie.startAnimation(poludnieAnim);
				layoutWieczor.startAnimation(wieczorAnim);
				layoutNoc.startAnimation(nocAnim);
				return false;
			}
		});

		layoutWieczor.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation ranoAnim = new HeightAnimation(layoutRano, 0);
				Animation poludnieAnim = new HeightAnimation(layoutPoludnie, 0);
				Animation wieczorAnim = new HeightAnimation(layoutWieczor, viewHeight / 3);
				Animation nocAnim = new HeightAnimation(layoutNoc, 0);

				ranoAnim.setDuration(500);
				poludnieAnim.setDuration(500);
				wieczorAnim.setDuration(500);
				nocAnim.setDuration(500);

				layoutRano.startAnimation(ranoAnim);
				layoutPoludnie.startAnimation(poludnieAnim);
				layoutWieczor.startAnimation(wieczorAnim);
				layoutNoc.startAnimation(nocAnim);
				return false;
			}
		});

		layoutNoc.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation ranoAnim = new HeightAnimation(layoutRano, 0);
				Animation poludnieAnim = new HeightAnimation(layoutPoludnie, 0);
				Animation wieczorAnim = new HeightAnimation(layoutWieczor, 0);
				Animation nocAnim = new HeightAnimation(layoutNoc, viewHeight / 3);

				ranoAnim.setDuration(500);
				poludnieAnim.setDuration(500);
				wieczorAnim.setDuration(500);
				nocAnim.setDuration(500);

				layoutRano.startAnimation(ranoAnim);
				layoutPoludnie.startAnimation(poludnieAnim);
				layoutWieczor.startAnimation(wieczorAnim);
				layoutNoc.startAnimation(nocAnim);
				return false;
			}
		});

	}

	public void setParamsToBar() {
		//int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, getResources().getDisplayMetrics());

		/*LinearLayout.LayoutParams paramsRano = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		paramsRano.weight = 1;
		layoutRano.setLayoutParams(paramsRano);

		LinearLayout.LayoutParams paramsPoludnie = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		paramsPoludnie.weight = 1;
		layoutPoludnie.setLayoutParams(paramsPoludnie);

		LinearLayout.LayoutParams paramsWieczor = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		paramsWieczor.weight = 1;
		layoutWieczor.setLayoutParams(paramsWieczor);

		LinearLayout.LayoutParams paramsNoc = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		paramsNoc.weight = 1;
		layoutNoc.setLayoutParams(paramsNoc);*/
	}

	public int getStatusBarHeight() {
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

		if (resourceId > 0) {
			return getResources().getDimensionPixelSize(resourceId);
		} else {
			return 0;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	protected class GetYrno extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			  try {
		            URL url = new URL("http://api.yr.no/weatherapi/locationforecast/1.9/?lat="+lat+";lon="+lon);
		            URLConnection conn = url.openConnection();

		            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            Document doc = builder.parse(conn.getInputStream());

		            NodeList nodes = doc.getElementsByTagName("temperature");
		            for (int i = 0; i < nodes.getLength(); i++) {
		                Element element = (Element) nodes.item(i);
		                NodeList title = element.getElementsByTagName("temperature");
		                Element line = (Element) title.item(0);
		              Log.d("SJ",nodes.item(i).getAttributes().item(2).getNodeValue());
		            }
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
			return null;
		}}
}
