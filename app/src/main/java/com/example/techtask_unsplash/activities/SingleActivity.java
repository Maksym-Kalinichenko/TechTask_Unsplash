package com.example.techtask_unsplash.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.techtask_unsplash.R;

public class SingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);


        ImageView fView = findViewById(R.id.fullImg);
        Intent receiver = getIntent();
        String sourceUrl = receiver.getStringExtra("imageUrl");
        Glide.with(this).load(sourceUrl).into(fView);
        
    }
}