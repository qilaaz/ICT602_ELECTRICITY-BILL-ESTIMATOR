package com.example.electricbill;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate; // Add this import

public class MainActivity extends AppCompatActivity {

    Button startButton, aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //  Force light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.buttonStart);
        aboutBtn = findViewById(R.id.buttonAbout);

        aboutBtn.setOnClickListener(v -> startActivity(new Intent(this, about.class)));

        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, billestimate.class);
            startActivity(intent);
        });
    }
}
