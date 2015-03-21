package com.example.pogodynka;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DrugieActivity extends Activity {

	TextView op1;
	TextView op2;
	TextView op3;

	private final LocationListener mLocationListener = new LocationListener() {
		@Override
		public void onLocationChanged(final Location location) {
			Log.d("XQ", "--------------------------------------------------\nlongitude: " + location.getLongitude() + "\nlatitude: " + location.getLatitude());
			Geocoder geo = new Geocoder(DrugieActivity.this, Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

				//JESLI GEOCODER NIE DZIALA (NP WALNIE ERRORA ZE CHUJ GO TO BOLI) TO WTEDY TRZEBA RESTARTOWAC TELEFON
				if(addresses.size() > 0) {
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drugie);

		op1 = (TextView) findViewById(R.id.op1);
		op2 = (TextView) findViewById(R.id.op2);
		op3 = (TextView) findViewById(R.id.op3);

		ObjectAnimator trans1 = ObjectAnimator.ofFloat(op1, "translationX", -100f, 0f).setDuration(300);
		ObjectAnimator al1 = ObjectAnimator.ofFloat(op1, "alpha", 0f, 1f).setDuration(300);
		trans1.start();
		al1.start();

		ObjectAnimator trans2 = ObjectAnimator.ofFloat(op2, "translationX", -100f, 0f).setDuration(300);
		ObjectAnimator al2 = ObjectAnimator.ofFloat(op2, "alpha", 0f, 1f).setDuration(300);
		trans2.setStartDelay(100);
		al2.setStartDelay(100);
		trans2.start();
		al2.start();

		ObjectAnimator trans3 = ObjectAnimator.ofFloat(op3, "translationX", -100f, 0f).setDuration(300);
		ObjectAnimator al3 = ObjectAnimator.ofFloat(op3, "alpha", 0f, 1f).setDuration(300);
		trans3.setStartDelay(200);
		al3.setStartDelay(200);
		trans3.start();
		al3.start();

		LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 5, mLocationListener);
	}



	@Override
	public void onBackPressed() {
		Zmienne.powrot = true;
		ObjectAnimator trans1 = ObjectAnimator.ofFloat(op1, "translationX", 0f, -100f).setDuration(150);
		ObjectAnimator al1 = ObjectAnimator.ofFloat(op1, "alpha", 1f, 0f).setDuration(150);
		trans1.setStartDelay(100);
		al1.setStartDelay(100);
		trans1.start();
		al1.start();

		ObjectAnimator trans2 = ObjectAnimator.ofFloat(op2, "translationX", 0f, -100f).setDuration(150);
		ObjectAnimator al2 = ObjectAnimator.ofFloat(op2, "alpha",  1f, 0f).setDuration(150);
		trans2.setStartDelay(50);
		al2.setStartDelay(50);
		trans2.start();
		al2.start();

		ObjectAnimator trans3 = ObjectAnimator.ofFloat(op3, "translationX", 0f, -100f).setDuration(150);
		ObjectAnimator al3 = ObjectAnimator.ofFloat(op3, "alpha", 1f, 0f).setDuration(150);
		trans3.start();
		al3.start();

		new java.util.Timer().schedule(
				new java.util.TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new   Runnable() {
							public void run() {
								finish();
								overridePendingTransition(0, 0);
							}
						});
					}
				}, 250);
	}
}
