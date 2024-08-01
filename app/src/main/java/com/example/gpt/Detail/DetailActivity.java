package com.example.gpt.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gpt.PembayaranPilihJam.MainActivity;
import com.example.gpt.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageViewDetail = findViewById(R.id.imageViewDetail);
        TextView textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        TextView textViewPriceDetail = findViewById(R.id.textViewPriceDetail);
        TextView textViewLocationDetail = findViewById(R.id.textViewLocationDetail);
        TextView textViewRatingDetail = findViewById(R.id.textViewRatingDetail);
        FloatingActionButton fabBooking = findViewById(R.id.fabBooking);

        // Get data from Intent
        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imageResId", -1);
        String title = intent.getStringExtra("title");
        String price = intent.getStringExtra("price");
        String location = intent.getStringExtra("location");
        String rating = intent.getStringExtra("rating");

        // Set data to views
        if (imageResId != -1) {
            imageViewDetail.setImageResource(imageResId);
        }
        textViewTitleDetail.setText(title);
        textViewPriceDetail.setText(price);
        textViewLocationDetail.setText(location);
        textViewRatingDetail.setText(rating);

        // Setup toolbar with back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fabBooking.setOnClickListener(v -> {
            Intent bookingIntent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(bookingIntent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
