package com.example.wangxiangyu.sliderlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isf = true;
    Slider slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView toolbar = (TextView) findViewById(R.id.toolbar);
        final FrameLayout p = (FrameLayout) findViewById(R.id.p);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("========","===onClick====");
                if (slider.isDown())
                {
                    slider.sliderUp();
                }
                else
                {
                    slider.sliderDown();
                }
            }
        });
        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (isf)
                {
                    isf = false;
                    Log.e("========","===onGlobalLayout===="+toolbar.getHeight());
                    slider  = new Slider(MainActivity.this,p,toolbar.getHeight()+toolbar.getY());
                }
            }
        });



    }
}
