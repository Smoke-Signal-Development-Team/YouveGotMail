package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SendConfirm extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_confirm);


        // Initialize the views.
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        TextView mailTypeEnv = findViewById(R.id.your_mail);
        //TextView poBoxInfo = findViewById(R.id.infoDetail);

        // Set the text from the Intent extra.
        poBoxTitle.setText(getIntent().getStringExtra("title")); // why isn't this working?
        mailTypeEnv.setText(getIntent().getStringExtra("type"));
        //poBoxInfo.setText(getIntent().getStringExtra("info"));

    }



    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

    public void launchConfirmSendActivity(View view) {
        displayToast(getString(R.string.confirm_push));
        Log.d(LOG_TAG, "Push Notification Sent!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void launchWrongTypeActivity(View view) {
        displayToast(getString(R.string.wrong_mail_type));
        Log.d(LOG_TAG, "Wrong Mail Type");
        Intent intent = new Intent(this, MailType.class);
        startActivity(intent);
    }

    public void launchWrongPOBoxActivity(View view) {
        displayToast(getString(R.string.wrong_po_box));
        Log.d(LOG_TAG, "Push Notification Sent!");
        Intent intent = new Intent(this, RegisteredBoxes.class);
        startActivity(intent);
    }
}
