package com.example.youvegotmail;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MailType extends AppCompatActivity {

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
}
