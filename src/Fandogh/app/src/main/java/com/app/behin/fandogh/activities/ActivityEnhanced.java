package com.app.behin.fandogh.activities;

import android.support.v7.app.AppCompatActivity;

import com.app.behin.fandogh.settings.G;


public class ActivityEnhanced extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        G.currentActivity = this;
        super.onResume();
    }
}
