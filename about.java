package com.example.electricbill;

import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.electricbill.R;

public class about extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        TextView aboutText = findViewById(R.id.textViewAbout);
        aboutText.setText("Name: Nur Aqila binti Azmawi\n" +
                "Student ID: 2024767503\n" +
                "Course: ICT602 - Mobile Technology & Development\n" +
                "© 2025 Nur Aqila\n" +
                "GitHub: https://github.com/yourusername/yourrepository");
        Linkify.addLinks(aboutText, Linkify.WEB_URLS);
    }
}