package com.example.icegh.notes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.io.FileNotFoundException;

@SuppressLint("ValidFragment")
public class DeleteDialog extends DialogFragment {
    int pos;
    String title;
    Notes notes;
    Context context;
    @SuppressLint("ValidFragment")
    public DeleteDialog(int pos, Context context,String title){
        this.context = context;
        this.title = title;
        this.pos = pos;
        try {
            notes = new Notes(context);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to delete this note \""+title+"\" ?")
                .setPositiveButton("Yes , Delete it", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    public void onClick(DialogInterface dialog, int id) {
                        if(notes.deleteNote(pos)){
                            Toast.makeText(getContext(),"Note deleted.",Toast.LENGTH_LONG).show();
                            getActivity().finish();
                        }else{

                        };

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
