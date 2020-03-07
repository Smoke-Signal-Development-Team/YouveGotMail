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

    String titleIntent;
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
        //poBoxTitle.setText(getIntent().getStringExtra("title"));
        poBoxInfo.setText(getIntent().getStringExtra("info"));
        poBoxTitle.setText(titleIntent);
        String titleIntent = getIntent().getStringExtra("title");
        poBoxTitle.setText(titleIntent);
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
        intent.putExtra("title", titleIntent);
        intent.putExtra("type", "Envelope");
        //intent.putExtra("title", "P.O. Box# 1006");
        //intent.putExtra("info", "John Wick");

        startActivity(intent);


        // Initialize the views.
        //TextView poBoxTitle = findViewById(R.id.titleDetail);
        //TextView mailTypeEnv = findViewById(R.id.your_mail);
        //TextView poBoxInfo = findViewById(R.id.infoDetail);

        // Set the text from the Intent extra.
        // poBoxTitle.setText(getIntent().getStringExtra("title"));
        //mailTypeEnv.setText(getIntent().getStringExtra("type"));
        //poBoxInfo.setText(getIntent().getStringExtra("info"));

    }

    public void launchPkgConfirmActivity(View view) {
        displayToast(getString(R.string.package_selected_confirmation));
        Log.d(LOG_TAG, "Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("type", "Package");
        //intent.putExtra("title", "P.O. Box# 1006");

        startActivity(intent);

    }

    public void launchEnvPkgConfirmActivity(View view) {
        displayToast(getString(R.string.envpkg_selected_confirmation));
        Log.d(LOG_TAG, "Envelope and Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("type", "Envelope & Package");
        //intent.putExtra("title", "P.O. Box# 1006");

        startActivity(intent);
    }

    public void launchMailboxFullActivity(View view) {
        displayToast(getString(R.string.mailbox_full_push));
        Log.d(LOG_TAG, "Mailbox Full Notification Sent!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
