package com.example.icegh.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NoteActivity extends AppCompatActivity {
    TextView titleTextView , contentTextView;
    public String title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleTextView = findViewById(R.id.title);
        contentTextView = findViewById(R.id.content);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        titleTextView.setText(title);
        contentTextView.setText(content);

    }
    public void share(View view){

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, title+"\n-------------\n"+content);
        startActivity(Intent.createChooser(sharingIntent, "sharing txt"));
    }
}
