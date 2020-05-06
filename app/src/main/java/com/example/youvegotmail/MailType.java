package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MailType extends AppCompatActivity {

    private String titleIntent;
    private String infoIntent;
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    //Sound Effect variables
    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_type);

        // Sound Effects Set Up
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
        //Sound Effect to work on older versions
        else {
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }

        //Sound Reference
        sound1 = soundPool.load(this, R.raw.env_sound, 1);
        sound2 = soundPool.load(this, R.raw.pkg_sound, 1);
        sound3 = soundPool.load(this, R.raw.env_pkg_sound, 1);
        sound4 = soundPool.load(this, R.raw.sad_trombone, 1);

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
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.envelope_mail_button);

        displayToast(getString(R.string.envelope_selected_confirmation));
        Log.d(LOG_TAG, "Envelope Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
        intent.putExtra("type", "Envelope");
        startActivity(intent);
        //Play Sound
        playSound(confirmSend);
    }

    public void launchPkgConfirmActivity(View view) {
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.package_mail_button);

        displayToast(getString(R.string.package_selected_confirmation));
        Log.d(LOG_TAG, "Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
        intent.putExtra("type", "Package");
        startActivity(intent);
        //Play Sound
        playSound(confirmSend);
    }

    public void launchEnvPkgConfirmActivity(View view) {
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.both_mail_button);

        displayToast(getString(R.string.envpkg_selected_confirmation));
        Log.d(LOG_TAG, "Envelope and Package Selected");
        Intent intent = new Intent(this, SendConfirm.class);
        intent.putExtra("title", titleIntent);
        intent.putExtra("info", infoIntent);
        intent.putExtra("type", "Envelope & Package");
        startActivity(intent);
        //Play Sound
        playSound(confirmSend);

    }

    public void launchMailboxFullActivity(View view) {
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.mailbox_full_button);

        displayToast(getString(R.string.mailbox_full_push));
        Log.d(LOG_TAG, "Mailbox Full Notification Sent!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //Play Sound
        playSound(confirmSend);
    }

    //Play Sound Effects
    public void playSound(View v) {
        switch (v.getId()) {
            case R.id.envelope_mail_button:
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                break;
            case R.id.package_mail_button:
                soundPool.play(sound2, 1, 1, 0, 0, 1);
                break;
            case R.id.both_mail_button:
                soundPool.play(sound3, 1, 1, 0, 0, 1);
                break;
            case R.id.mailbox_full_button:
                soundPool.play(sound4, 1, 1, 0, 0, 1);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
