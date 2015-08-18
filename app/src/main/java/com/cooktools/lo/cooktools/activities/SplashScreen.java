package com.cooktools.lo.cooktools.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cooktools.lo.cooktools.R;

/**
 * SplashScreen: s'affiche au lancement de l'application.
 */

public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Home.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}