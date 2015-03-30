package com.example.pogodynka;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private int viewHeight;
	
	private ImageView kolko;
	private ImageView kolko2;
	private ImageView imidzwiew;

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
		
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		viewHeight = size.y - getStatusBarHeight();

		layoutRano.getLayoutParams().height = viewHeight / 3;
		
		setListeners();
		

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

}
