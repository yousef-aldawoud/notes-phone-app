package com.example.icegh.notes;

import android.content.Context;

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

public class Notes {
    JSONArray notesJSONArray;
    File file ;
    FileOutputStream outputStream;
    public Context context;

    public Notes(Context applicationContext) throws FileNotFoundException {;
        this.context = applicationContext;

        file =new File(context.getFilesDir(),"notes.json");

    }
    public void checkNotesFile(){
        if (file.exists()){
            System.out.println("file exists");
        }else{
            try {
                file.createNewFile();
                outputStream  = context.openFileOutput("notes.json", Context.MODE_PRIVATE);
                outputStream.write("[]".getBytes());
                outputStream.close();
            } catch (IOException e) {
                System.out.println("shit happend");
                e.printStackTrace();
            }
        }
    }
    public JSONArray getNotesArray() throws FileNotFoundException, JSONException {
            notesJSONArray= new JSONArray(loadFile());
            return notesJSONArray;

    }
    String  loadFile() throws FileNotFoundException {
        String txt = "";
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            txt = txt +sc.nextLine();
        }
        return  txt;
    }
    public String saveNote(String title,String content){
        String response ="";
        try {
            JSONObject noteObject = new JSONObject();
            noteObject.put("title",title);
            noteObject.put("content",content);
            notesJSONArray.put(noteObject);
            outputStream = context.openFileOutput("notes.json", Context.MODE_PRIVATE);
            outputStream.write(notesJSONArray.toString().getBytes());
            outputStream.close();
            response = "success";
        } catch (Exception e) {
            response = "error";
        }

        return response;
    }
}
