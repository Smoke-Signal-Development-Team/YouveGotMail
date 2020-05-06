package com.example.youvegotmail;
import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    //Sound Effect variables
    private SoundPool soundPool;
    private int sound1, sound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        sound1 = soundPool.load(this, R.raw.scan_button_sound, 1);
        sound2 = soundPool.load(this, R.raw.manual_input_sound, 1);


    }

    public void scan(View view) {
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.scan_button);

        Log.d(LOG_TAG, "Scan Button clicked!");
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
        //Play Sound
        playSound(confirmSend);
    }

    public void manualInput(View view) {
        //Sound Reference
        final ImageView confirmSend;
        confirmSend = findViewById(R.id.man_button);

        Log.d(LOG_TAG, "Manual Button clicked!");
        Intent intent = new Intent(this, RegisteredBoxes.class);
        startActivity(intent);
        //Play Sound
        playSound(confirmSend);
    }

    //Play Sound Effects
    public void playSound(View v) {
        switch (v.getId()) {
            case R.id.scan_button:
                soundPool.play(sound1, 1, 1, 0, 0, 1);
                break;
            case R.id.man_button:
                soundPool.play(sound2, 1, 1, 0, 0, 1);
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
