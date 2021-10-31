package com.imagegrafia.androidbasic;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    long animTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView iv_logo_splash = findViewById(R.id.iv_logo_splash);
        TextView logo_txt = findViewById(R.id.logo_txt);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_logo_splash, "y", 400f);
        ObjectAnimator animatorName = ObjectAnimator.ofFloat(logo_txt, "x", 400f);

        animatorY.setDuration(animTime);
        animatorName.setDuration(animTime);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorY, animatorName);
        animatorSet.start();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}