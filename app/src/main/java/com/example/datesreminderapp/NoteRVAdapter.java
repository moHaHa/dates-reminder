package com.example.datesreminderapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datesreminderapp.models.Agent;
import com.example.datesreminderapp.models.Note;

import java.util.ArrayList;

public class NoteRVAdapter extends RecyclerView.Adapter<NoteRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Note> noteArrayList;
    private Context context;
    Button btnPostpn ;

    // constructor
    public NoteRVAdapter(ArrayList<Note> courseModalArrayList, Context context) {
        this.noteArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_note_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Note modal = noteArrayList.get(position);
        holder.noteTitle.setText(modal.getTitle());
        holder.noteTime.setText(modal.getTime());
        holder.noteDate.setText(modal.getDate());
        holder.noteAgentName.setText(modal.getAgentName());
        holder.noteDependency.setText(modal.getDependencyName());
        holder.noteDescription.setText(modal.getDescription());


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return noteArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView  noteTitle, noteDate , noteTime , noteAgentName , noteDependency  , noteDescription;
        private  Button done , postpon , close;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            noteTitle = itemView.findViewById(R.id.id_title);
            noteDate = itemView.findViewById(R.id.id_date);
            noteTime = itemView.findViewById(R.id.id_time);
            noteAgentName = itemView.findViewById(R.id.id_agentName);
            noteDependency = itemView.findViewById(R.id.id_dependencyName);
            noteDescription = itemView.findViewById(R.id.id_description);

            done = itemView.findViewById(R.id.id_done);
            close = itemView.findViewById(R.id.id_close);
            postpon = itemView.findViewById(R.id.id_postpon);

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Task Is Done !", Toast.LENGTH_SHORT).show();
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Task Closed !", Toast.LENGTH_SHORT).show();
                }
            });
            postpon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "something went wrong (timezone) !", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}