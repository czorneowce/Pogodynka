package com.example.pogodynka;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
		
		//https://dribbble.com/shots/1980005--CSS-Menu-animation?list=shots&sort=popular&timeframe=now&offset=6
	}
	
	@Override
	protected void onResume() {
		if (Zmienne.powrot == true) {
			ObjectAnimator oa = ObjectAnimator.ofFloat(kolko, "scaleX", 15.0f, 1.0f).setDuration(500);
			ObjectAnimator ob = ObjectAnimator.ofFloat(kolko, "scaleY", 15.0f, 1.0f).setDuration(500);
			oa.start();
			ob.start();
			Zmienne.powrot = false;
		}
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
