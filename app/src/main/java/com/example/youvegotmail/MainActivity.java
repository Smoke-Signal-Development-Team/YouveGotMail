package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan_activity(View view) {
        Log.d(LOG_TAG, "Scan Button clicked!");
        Intent intent = new Intent(this, scan_activity.class);
        startActivity(intent);
    }

    public void manual_activity(View view) {
        Log.d(LOG_TAG, "Manual Button clicked!");
        Intent intent = new Intent(this, manual_activity.class);
        startActivity(intent);
    }
}
