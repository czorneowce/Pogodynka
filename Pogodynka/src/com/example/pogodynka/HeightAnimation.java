package com.example.pogodynka;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class HeightAnimation extends Animation {
	
	private int targetHeight;
    private int startingHeight;
    private View view;
    private boolean increase;

    public HeightAnimation(View view, int targetHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.startingHeight = this.view.getLayoutParams().height;
        
        if(this.startingHeight - targetHeight > 0) {
        	increase = false;
        } else {
        	increase = true;
        }
    }
    
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
    	if(increase) {
    		view.getLayoutParams().height = (int) (this.startingHeight + (targetHeight - this.startingHeight) * interpolatedTime);
    	} else {
    		view.getLayoutParams().height = (int) (this.startingHeight - (this.startingHeight - targetHeight) * interpolatedTime);
    	}
        view.requestLayout();
    }

    @Override
    public boolean willChangeBounds() {
        return false;
    }
}
