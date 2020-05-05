package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SendConfirm extends AppCompatActivity {

    String titleIntent;
    String infoIntent;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_confirm);

        /* Initialize the views. */
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        TextView mailTypeEnv = findViewById(R.id.your_mail);
        TextView poBoxInfo = findViewById(R.id.infoDetail);
        //TextView poBoxInfo = null;

        /* Set the text from the Intent extra. */
        // Gets the mail type
        mailTypeEnv.setText(getIntent().getStringExtra("type"));

        // Gets the PO Box #
        // poBoxTitle.setText(getIntent().getStringExtra("title"));
        titleIntent = getIntent().getStringExtra("title");
        poBoxTitle.setText(titleIntent);

        // Gets the name
        // poBoxInfo.setText(getIntent().getStringExtra("info"));
        infoIntent = getIntent().getStringExtra("info");
        poBoxInfo.setText(infoIntent);

        //Sound Effect
        final MediaPlayer youGotMailSE = MediaPlayer.create(this, R.raw.you_got_mail_w);
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.confirm_send_button);
        confirmSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youGotMailSE.start();
                //Initiate intent after sound effect
                launchConfirmSendActivity(confirmSend);
        }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
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
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
        startActivity(intent);
    }

    public void launchWrongPOBoxActivity(View view) {
        displayToast(getString(R.string.wrong_po_box));
        Log.d(LOG_TAG, "Push Notification Sent!");
        Intent intent = new Intent(this, RegisteredBoxes.class);
        startActivity(intent);
    }
}
