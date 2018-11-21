package com.example.icegh.notes;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AddNoteActivity extends AppCompatActivity {
    File file ;
    EditText title;
    EditText noteContent;
    FileOutputStream outputStream;
    JSONArray notesJSONArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        file =new File(getApplicationContext().getFilesDir(),"notes.json");
        title = (EditText)findViewById(R.id.title);
        noteContent = (EditText)findViewById(R.id.note_context);
        System.out.println("KK:"+file.getAbsolutePath());
        if (file.exists()){
            System.out.println("file exists");
        }else{
            try {
                file.createNewFile();
                System.out.println("file created ::"+file.getAbsolutePath());
                outputStream = openFileOutput("notes.json", Context.MODE_PRIVATE);
                outputStream.write("[]".getBytes());
                outputStream.close();
            } catch (IOException e) {
                System.out.println("shit happend");
                e.printStackTrace();
            }
        }
        try {
            System.out.println("file content :: "+loadFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            notesJSONArray= new JSONArray(loadFile());
        } catch (JSONException e) {
            title.setText("error 7");
        } catch (FileNotFoundException e) {
            title.setText("error 8");
        }
    }


    public String saveNote(String title,String content){
        String response ="";
        try {
            JSONObject noteObject = new JSONObject();
            noteObject.put("title",title);
            noteObject.put("content",content);
            notesJSONArray.put(noteObject);
            outputStream = openFileOutput("notes.json", Context.MODE_PRIVATE);
            outputStream.write(notesJSONArray.toString().getBytes());
            outputStream.close();
            response = "success";
        } catch (Exception e) {
            response = "error";
        }

        return response;
    }
    String  loadFile() throws FileNotFoundException {
            String txt = "";
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()){
                txt = txt +sc.nextLine();
            }
        return  txt;
    }
    public void save(View view){

        Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_LONG).show();
        String x = saveNote(title.getText().toString(),noteContent.getText().toString());
        Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
        System.out.println(x);
    }
    public void say(View view){

        Toast.makeText(getApplicationContext(),"SAY",Toast.LENGTH_LONG).show();
        try {
            String x = loadFile();
            Toast.makeText(getApplicationContext(),x,Toast.LENGTH_LONG).show();
            System.out.println(x);
        } catch (FileNotFoundException e) {

            Toast.makeText(getApplicationContext(),"Nooo",Toast.LENGTH_LONG).show();
            System.out.println("NOOOO");
        }
    }
}
