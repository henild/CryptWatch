package com.example.cryptwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptwatch.LoginInfo.Login;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 4000;
    Animation topAnim , bottomAnim;
    ImageView logo;
    TextView appName , tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = (ImageView) findViewById(R.id.logo);
        appName = (TextView) findViewById(R.id.appname);
        tagline = (TextView) findViewById(R.id.tagline);

        logo.setAnimation(topAnim);
        appName.setAnimation(bottomAnim);
        tagline.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(logo, "logo_image");
                pairs[1] = new Pair<View,String>(appName, "logo_text");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                startActivity(intent,activityOptions.toBundle());
                finish();
            }
        },SPLASH_TIME);

    }
}