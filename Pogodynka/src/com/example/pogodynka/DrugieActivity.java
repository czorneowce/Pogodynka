package com.example.pogodynka;

import java.util.List;
import java.util.Locale;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
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
		
		try{
			Geocoder geo = new Geocoder(DrugieActivity.this.getApplicationContext(), Locale.getDefault());
			List<Address> addresses = geo.getFromLocation(50, 50, 1);  
			Log.d("XQ","Ds");
			if(addresses.size() > 0) {
				Log.d("XQ","Dhs");
			Log.d("XQ",addresses.get(0).getFeatureName() + ", "
					+ addresses.get(0).getLocality() +", "
					+ addresses.get(0).getAdminArea() + ", "
					+ addresses.get(0).getCountryName());
			} else {
				Log.d("XQ","zero");
			}

		}
		catch (Exception e) {
			e.printStackTrace(); 
		}

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
