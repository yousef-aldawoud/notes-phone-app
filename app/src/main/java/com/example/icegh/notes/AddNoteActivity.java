package com.example.icegh.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;

public class AddNoteActivity extends AppCompatActivity {
    File file ;
    EditText title;
    EditText noteContent;
    JSONArray notesJSONArray;
    Notes notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        file =new File(getApplicationContext().getFilesDir(),"notes.json");
        title = findViewById(R.id.title);
        noteContent = findViewById(R.id.note_context);

        try {
            notes = new Notes(getApplicationContext());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            notesJSONArray = notes.getNotesArray();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"shit went down 22",Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"shit went down 33",Toast.LENGTH_LONG).show();
        }


    }

    public void save(View view){
        Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_LONG).show();
        String x = notes.saveNote(title.getText().toString(),noteContent.getText().toString());
        Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
        this.finish();
    }
    public void say(View view){
        try {
            String x = notes.loadFile();
            Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"Nooo",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
