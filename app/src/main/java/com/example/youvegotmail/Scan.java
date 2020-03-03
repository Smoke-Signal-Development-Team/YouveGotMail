package com.example.youvegotmail;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Scan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

<<<<<<< HEAD
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    //Shows message for scan confirmation
    public void showScanConfirmation(View view) {
        displayToast(getString(R.string.scan_confirmation));
        Log.d(LOG_TAG, "Scan Button clicked!");
        Intent intent = new Intent(this, MailType.class);
        intent.putExtra("title", "P.O. Box# 1006");
        intent.putExtra("info", "John Wick");
        startActivity(intent);
    }
=======
>>>>>>> parent of 359ea93... Test 03-02
}
