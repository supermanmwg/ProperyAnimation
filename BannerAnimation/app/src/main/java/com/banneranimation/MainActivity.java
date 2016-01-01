package com.banneranimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView helloTv;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloTv = (TextView) findViewById(R.id.hello);

        final ObjectAnimator down = new ObjectAnimator().ofFloat(helloTv, "rotation", 2);
        down.setDuration(100);
        down.setInterpolator(new AccelerateInterpolator());


        final ObjectAnimator up = new ObjectAnimator().ofFloat(helloTv, "rotation", -2);
        up.setDuration(100);
        up.setInterpolator(new AccelerateInterpolator());

        final AnimatorSet bounce = new AnimatorSet();
        bounce.setStartDelay(1000);
        bounce.playSequentially(down, up);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                bounce.start();
                Log.d("haha", "bounce is starting!");
                handler.postDelayed(this, 200);
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != handler) {
            handler.removeCallbacks(runnable);
        }
    }
}
