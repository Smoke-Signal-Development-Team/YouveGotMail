package com.example.youvegotmail;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Scan extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    //Sound Effect variables
    private SoundPool soundPool;
    private int sound1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_activity);

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
        sound1 = soundPool.load(this, R.raw.scan_confirm, 1);

    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    //Shows message for scan confirmation
    public void showScanConfirmation(View view) {
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.imageView); // This is incorrect

        displayToast(getString(R.string.scan_confirmation));
        Log.d(LOG_TAG, "Scan Button clicked!");
        Intent intent = new Intent(this, MailType.class);
        intent.putExtra("title", "P.O. Box# 1006");
        intent.putExtra("info", "John Wick");
        startActivity(intent);

        //Play Sound
        playSound(confirmSend);
    }

    //Play Sound Effects
    public void playSound(View v) {
        switch (v.getId()) {
            case R.id.imageView: //This is incorrect
                soundPool.play(sound1, 1, 1, 0, 0, 1);
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
