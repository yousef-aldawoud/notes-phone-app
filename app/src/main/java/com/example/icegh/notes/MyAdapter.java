package com.example.icegh.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private JSONArray values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleTextView;
        public TextView contentTextView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;

            titleTextView = (TextView) v.findViewById(R.id.title);
            contentTextView = (TextView) v.findViewById(R.id.content);
        }
    }
    public void setValues(JSONArray values){
        this.values = values;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(JSONArray myDataSet,Context context) {
        values = myDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.note_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final JSONObject jsonObject;
        try {
            jsonObject = values.getJSONObject(position);
            if(jsonObject.getString("title").length()>60){
                holder.titleTextView.setText(jsonObject.getString("title").substring(0,50)+".........");
            }else{
                holder.titleTextView.setText(jsonObject.getString("title"));
            }

            if(jsonObject.getString("content").length()>200){
                holder.contentTextView.setText(jsonObject.getString("content").substring(0,200)+".........");
            }else {
                holder.contentTextView.setText(jsonObject.getString("content"));
            }
                holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),NoteActivity.class);

                    try {
                        System.out.println("pos::"+position+"\\");
                        System.out.println("pos::"+values.getJSONObject(position).getString("title"));
                        System.out.println("pos::"+values.getJSONObject(position).getString("content"));
                        Toast.makeText(v.getContext(),":::"+values.getJSONObject(position).getString("title"),Toast.LENGTH_LONG);
                        Toast.makeText(v.getContext(),":::"+values.getJSONObject(position).getString("content"),Toast.LENGTH_LONG);
                        intent.putExtra("title",values.getJSONObject(position).getString("title"));
                        intent.putExtra("content",values.getJSONObject(position).getString("content"));
                        v.getContext().startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.length();
    }

}