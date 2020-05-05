package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SendConfirm extends AppCompatActivity {

    String titleIntent;
    String infoIntent;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    //Sound Effect variables
    private SoundPool soundPool;
    private int sound1, sound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_confirm);

        // Sound Effects
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        sound1 = soundPool.load(this, R.raw.you_got_mail_w, 1);

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

    //Play Sound Effects
    public void playSound(View v) {

        switch (v.getId()) {
            case R.id.confirm_send_button:
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                break;
        }
        confirmSend = findViewById(R.id.confirm_send_button);;
        launchConfirmSendActivity(confirmSend);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
