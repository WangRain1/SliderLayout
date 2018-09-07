package com.example.wangxiangyu.sliderlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Created by wangxiangyu on 2018/9/7.
 */

public class Slider
{

    View slider;
    boolean isDown;
    int sliderHeight;
    float mTop;
    private boolean isf = true;

    public Slider(final Context context, final FrameLayout p, float top)
    {

        mTop = top;

        slider =LayoutInflater.from(context).inflate(R.layout.sliderlayout,p);
        slider.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (isf){
                    isf = false;
                    sliderHeight = (int) (slider.getHeight() - mTop);
                    reset(-sliderHeight);
                }

            }
        });
    }

    public void reset(int curH)
    {
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(slider.getLayoutParams());
        params.topMargin = curH;
        Log.e("========" + mTop,"====s===" + slider.getHeight());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(params);

        slider.setLayoutParams(layoutParams);
    }

    public void sliderDown()
    {
        isDown = true;

        ValueAnimator animator = ValueAnimator.ofInt(-sliderHeight,(int)mTop);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                reset((Integer) animation.getAnimatedValue());
            }
        });
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    public void sliderUp()
    {
        isDown = false;
        
        ValueAnimator animator = ValueAnimator.ofInt((int)mTop,-sliderHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                reset((Integer) animation.getAnimatedValue());
            }
        });
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    public boolean isDown()
    {
        return isDown;
    }

}
