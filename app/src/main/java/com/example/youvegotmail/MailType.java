package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

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

        // Set the text from the Intent extra.
        poBoxTitle.setText(getIntent().getStringExtra("title"));
        poBoxInfo.setText(getIntent().getStringExtra("info"));
    }

    public void launchEnvConfirmActivity(View view) {
        Log.d(LOG_TAG, "Envelope Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        startActivity(intent);

        // Initialize the views.
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        TextView poBoxInfo = findViewById(R.id.infoDetail);

        // Set the text from the Intent extra.
        poBoxTitle.setText(getIntent().getStringExtra("title"));
        poBoxInfo.setText(getIntent().getStringExtra("info"));

    }



}
