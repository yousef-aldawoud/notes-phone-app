package com.example.icegh.notes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    TextView titleTextView , contentTextView;
    public String title,content,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        titleTextView = findViewById(R.id.title);
        contentTextView = findViewById(R.id.content);
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
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
    public void copyToClipboard(View v){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(title, title+"\n-------------\n"+content);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(),"Copied to clipboard",Toast.LENGTH_SHORT).show();
    }
    public void delete(View v){
        DeleteDialog deleteDialog = new DeleteDialog(Integer.parseInt(id),getApplicationContext(),title);
        deleteDialog.show(getSupportFragmentManager(),"delete");
    }
    public void edit(View v){
        Intent intent = new Intent(getApplicationContext(),AddNoteActivity.class);
        intent.putExtra("type","edit");
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        intent.putExtra("id",id);
        this.finish();
        startActivity(intent);
    }
}
