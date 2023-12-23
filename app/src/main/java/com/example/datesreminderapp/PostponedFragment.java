package com.example.datesreminderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datesreminderapp.models.Note;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostponedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostponedFragment extends Fragment {


    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<Note> noteModalArrayList;
    private DatabaseHelper2 databaseHelper2;
    private NoteRVAdapter noteRVAdapter;
    private RecyclerView notesRV;

    public PostponedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    public static PostponedFragment newInstance(String param1, String param2) {
        PostponedFragment fragment = new PostponedFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_postponed, container, false);
        // initializing our all variables.


        return view;
    }
}