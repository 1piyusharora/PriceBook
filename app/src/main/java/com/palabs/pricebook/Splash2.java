package com.palabs.pricebook;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash2 extends AppCompatActivity {

    ImageView i2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash2);

        MediaPlayer ring= MediaPlayer.create(Splash2.this,R.raw.codexaudio);
        ring.start();

        i2 = (ImageView) findViewById(R.id.imageView2);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        final Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        i2.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {



            }

            @Override
            public void onAnimationEnd(Animation animation) {
                i2.setAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation2.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                i2.setVisibility(View.GONE);
                startActivity(new Intent(getApplicationContext(), AllItems.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
