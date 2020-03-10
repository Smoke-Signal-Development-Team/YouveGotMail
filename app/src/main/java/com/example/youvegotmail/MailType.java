package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;


public class MailType extends AppCompatActivity {

    private String titleIntent;
    private String infoIntent;

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_type);


        /* Initialize the views. */
        // TextView mailTypeEnv = findViewById(R.id.infoDetail); ~ This can probably go
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        TextView poBoxInfo = findViewById(R.id.infoDetail);


        /* Set the text from the Intent extra. */
        // Gets the PO Box #
        // poBoxTitle.setText(getIntent().getStringExtra("title"));
        titleIntent = getIntent().getStringExtra("title");
        poBoxTitle.setText(titleIntent);

        // Gets the name
        // mailTypeEnv.setText(getIntent().getStringExtra(typeE); ~ This can probably go
        // poBoxInfo.setText(getIntent().getStringExtra("info"));
        infoIntent = getIntent().getStringExtra("info");
        poBoxInfo.setText(infoIntent);




    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void launchEnvConfirmActivity(View view) {
        displayToast(getString(R.string.envelope_selected_confirmation));
        Log.d(LOG_TAG, "Envelope Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
        intent.putExtra("type", "Envelope");

        //intent.putExtra("title", "P.O. Box# 1006");
        //intent.putExtra("info", "John Wick");

        startActivity(intent);

        /*  I don't think we need this.

        // Initialize the views.
        //TextView poBoxTitle = findViewById(R.id.titleDetail);
        //TextView mailTypeEnv = findViewById(R.id.your_mail);
        //TextView poBoxInfo = findViewById(R.id.infoDetail);

        // Set the text from the Intent extra.
        //poBoxTitle.setText(getIntent().getStringExtra("title"));
        //mailTypeEnv.setText(getIntent().getStringExtra("type"));
        //poBoxInfo.setText(getIntent().getStringExtra("info"));

         */


    }

    public void launchPkgConfirmActivity(View view) {
        displayToast(getString(R.string.package_selected_confirmation));
        Log.d(LOG_TAG, "Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
        intent.putExtra("type", "Package");
        //intent.putExtra("title", "P.O. Box# 1006");

        startActivity(intent);

    }

    public void launchEnvPkgConfirmActivity(View view) {
        displayToast(getString(R.string.envpkg_selected_confirmation));
        Log.d(LOG_TAG, "Envelope and Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
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
