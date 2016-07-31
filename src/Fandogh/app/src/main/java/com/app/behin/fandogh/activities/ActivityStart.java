package com.app.behin.fandogh.activities;

import android.content.Intent;
import android.os.Bundle;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;


public class ActivityStart extends ActivityEnhanced {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        G.HANDLER.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(ActivityStart.this, ActivityMain.class);
                ActivityStart.this.startActivity(intent);
                ActivityStart.this.finish();
            }
        }, 2000);
    }

}