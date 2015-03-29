package com.example.pogodynka;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DrugieActivity extends Activity {

	private TextView op1;
	private TextView op2;
	private TextView op3;
	private LocationManager mLocationManager;


	/*Button btnGPSShowLocation;
	Button btnShowAddress;
	TextView tvAddress;

	AppLocationService appLocationService;

	private DataBase dbManager;*/

	/*	TO JEST TE¯ COŒ Z GEOCODINGU TAK JAK TAM GDZIEŒ NI¯EJ TYLKO ¯E TO NIE ZAWSZE DZIALA WIÊC RÓWNIE DOBRZE MO¯E ZOSTAÆ USUNIÊTE
	 
 	private final LocationListener mLocationListener = new LocationListener() {
		@Override
		public void onLocationChanged(final Location location) {
			Log.d("XQ", "--------------------------------------------------\nlongitude: " + location.getLongitude() + "\nlatitude: " + location.getLatitude());
			Geocoder geo = new Geocoder(DrugieActivity.this, Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

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
	};*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drugie);

		String text = "Polska Kraków Nowaczyñskiego 5";
		Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
		List<Address> coordinatesList = null;
		try {
			coordinatesList = geocoder.getFromLocationName(text,   1);
		} catch (IOException e) {
		}
		if (coordinatesList != null && coordinatesList.size() > 0) {
			Address address = coordinatesList.get(0);
			Log.d("XQ", address.getLatitude() + " " + address.getLongitude());
		}

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

		
		
		
		
		/* TA CZESC JEST DO BAZY DANYCH

		 dbManager = new DataBase(DrugieActivity.this, "baza_miast", null, 1);

		dbManager.getWritableDatabase();

		dbManager.deleteData("2");
		Cursor x = dbManager.getData();

		while(x.moveToNext()){
			Log.d("XQ",x.getString(0));
			Log.d("XQ",x.getString(1));
			Log.d("XQ", x.getColumnIndex("id") + " " + x.getColumnName(1) + " " + x.getColumnCount());

		}*/

		
		
		
		
		
		/* TA CZESC JEST DO GEOCODINGU W POJÊCIU: KOORDYNATY-->NAZWA
		 * ALE CHYBA SIE I TAK NIE PRZYDA

		  tvAddress = (TextView) findViewById(R.id.tvAddress);
		appLocationService = new AppLocationService(DrugieActivity.this);

		btnGPSShowLocation = (Button) findViewById(R.id.btnGPSShowLocation);
		btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Location gpsLocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
				if (gpsLocation != null) {
					double latitude = gpsLocation.getLatitude();
					double longitude = gpsLocation.getLongitude();
					String result = "Latitude: " + gpsLocation.getLatitude() +
							" Longitude: " + gpsLocation.getLongitude();
					tvAddress.setText(result);
				} else {
					showSettingsAlert();
				}
			}
		});

		btnShowAddress = (Button) findViewById(R.id.btnShowAddress);
		btnShowAddress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Location location = appLocationService.getLocation(LocationManager.GPS_PROVIDER);

				//you can hard-code the lat & long if you have issues with getting it
				//remove the below if-condition and use the following couple of lines
				//double latitude = 37.422005;
				//double longitude = -122.084095

				if (location != null) {
					double latitude = location.getLatitude();
					double longitude = location.getLongitude();
					LocationAddress locationAddress = new LocationAddress();
					locationAddress.getAddressFromLocation(latitude, longitude,
							getApplicationContext(), new GeocoderHandler());
				} else {
					showSettingsAlert();
				}

			}
		});
		 */
	}
	/*	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				DrugieActivity.this);
		alertDialog.setTitle("SETTINGS");
		alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				DrugieActivity.this.startActivity(intent);
			}
		});
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

	private class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
			String locationAddress;
			switch (message.what) {
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				break;
			default:
				locationAddress = null;
			}
			tvAddress.setText(locationAddress);
		}
	}*/



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
