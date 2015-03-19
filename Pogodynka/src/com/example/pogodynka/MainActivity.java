package com.example.pogodynka;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView kolko;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		kolko = (ImageView) findViewById(R.id.kolko);

		final MainActivity that = this;
		
		kolko.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation anim = new AnimationUtils().loadAnimation(that, R.anim.zwieksz_kolo);
				kolko.startAnimation(anim);
				return false;
			}
		});
		
		//https://dribbble.com/shots/1980005--CSS-Menu-animation?list=shots&sort=popular&timeframe=now&offset=6
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
