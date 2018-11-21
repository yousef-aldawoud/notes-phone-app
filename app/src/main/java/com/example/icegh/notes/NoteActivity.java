package com.example.icegh.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    TextView titleTextView , contentTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleTextView = findViewById(R.id.title);
        contentTextView = findViewById(R.id.content);
        titleTextView.setText(getIntent().getStringExtra("title"));
        contentTextView.setText(getIntent().getStringExtra("content"));

    }
}
