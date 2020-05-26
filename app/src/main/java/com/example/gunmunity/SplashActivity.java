package com.example.gunmunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gunmunity.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashAnimation();
    }

    @UiThread
    public void splashAnimation() {
        ImageView splashBg = findViewById(R.id.splash_bg);
        Log.d("MyTag", splashBg.toString());
        Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        splashBg.startAnimation(imageAnim);

        imageAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        overridePendingTransition(R.anim.splash_out_bottom, R.anim.splash_out_top);
        startActivity(intent);
        finish();
    }
}
