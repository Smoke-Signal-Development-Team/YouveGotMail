package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MailType extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_type);


        // Initialize the views.
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        TextView poBoxInfo = findViewById(R.id.infoDetail);
        //TextView mailTypeEnv = findViewById(R.id.infoDetail);

        // Set the text from the Intent extra.
        poBoxTitle.setText(getIntent().getStringExtra("title"));
        poBoxInfo.setText(getIntent().getStringExtra("info"));
        //mailTypeEnv.setText(getIntent().getStringExtra(typeE);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

    public void launchEnvConfirmActivity(View view) {
        displayToast(getString(R.string.envelope_selected_confirmation));
        Log.d(LOG_TAG, "Envelope Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", "P.O. Box# 1006");
        //intent.putExtra("info", "John Wick");
        intent.putExtra("type", "Envelope");
        startActivity(intent);


        // Initialize the views.
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        //TextView poBoxInfo = findViewById(R.id.infoDetail);

        // Set the text from the Intent extra.
        poBoxTitle.setText(getIntent().getStringExtra("title"));
        //poBoxInfo.setText(getIntent().getStringExtra("info"));


    }

    public void launchPkgConfirmActivity(View view) {
        displayToast(getString(R.string.package_selected_confirmation));
        Log.d(LOG_TAG, "Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        startActivity(intent);

    }

    public void launchEnvPkgConfirmActivity(View view) {
        displayToast(getString(R.string.envpkg_selected_confirmation));
        Log.d(LOG_TAG, "Envelope and Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        startActivity(intent);
    }
}
