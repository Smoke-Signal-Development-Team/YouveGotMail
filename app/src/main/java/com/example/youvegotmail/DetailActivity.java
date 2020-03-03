package com.example.youvegotmail;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    /**
     * Initializes the activity, filling in the data from the Intent.
     *
     * @param savedInstanceState Contains information about the saved state
     *                           of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize the views.
        TextView poBoxTitle = findViewById(R.id.titleDetail);
        TextView poBoxInfo = findViewById(R.id.infoDetail);
        ImageView poBoxImage = findViewById(R.id.poBoxImageDetail);

        // Set the text from the Intent extra.
        poBoxTitle.setText(getIntent().getStringExtra("title"));
        poBoxInfo.setText(getIntent().getStringExtra("info"));

        // Load the image using the Glide library and the Intent extra.
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(poBoxImage);
    }
}
