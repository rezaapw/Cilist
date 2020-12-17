package com.example.cilist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.title);
        TextView popularity = findViewById(R.id.popularity);
        TextView tanggal = findViewById(R.id.tanggal);
        TextView bahasa = findViewById(R.id.bahasa);
        TextView overview = findViewById(R.id.overview);
        ImageView image = findViewById(R.id.image);

        title.setText(getIntent().getStringExtra("title"));
        popularity.setText("Popularitas: "+getIntent().getStringExtra("popularity"));
        bahasa.setText("Bahasa: "+getIntent().getStringExtra("bahasa"));
        tanggal.setText("Tanggal Tayang: "+getIntent().getStringExtra("tanggal"));
        overview.setText("Overview: "+getIntent().getStringExtra("overview"));
        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w200"+getIntent().getStringExtra("image")).into(image);
    }
}