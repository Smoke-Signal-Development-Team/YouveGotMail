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

    public void scan(View view) {
        Log.d(LOG_TAG, "Scan Button clicked!");
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
    }

    public void manualInput(View view) {
        Log.d(LOG_TAG, "Manual Button clicked!");
        Intent intent = new Intent(this, ManualInput.class);
        startActivity(intent);
    }

    /* Not sure if this section belongs in MainActivity or MailType
    public void launchEnvConfirmActivity(View view) {
        Log.d(LOG_TAG, "Envelope Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        startActivity(intent);
    }
    */


}
