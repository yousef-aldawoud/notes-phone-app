package com.example.icegh.notes;

import android.content.Context;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    public JSONArray search(String regex){
        JSONArray result = new JSONArray();
        regex = ".*"+regex+".*";
        try {
            JSONArray notes = getNotesArray();
            for(int i =0 ; i<notes.length() ;i++){
                JSONObject note = (JSONObject) notes.get(i);
                if(Pattern.matches(regex,note.getString("content"))||Pattern.matches(regex,note.getString("title"))){
                    result.put(note);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
