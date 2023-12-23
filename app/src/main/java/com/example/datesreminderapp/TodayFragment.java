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


public class TodayFragment extends Fragment {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<Note> noteModalArrayList;
    private DatabaseHelper2 databaseHelper2;
    private NoteRVAdapter noteRVAdapter;
    private RecyclerView notesRV;

    public TodayFragment() {
        // Required empty public constructor
    }

    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_today, container, false);
        // initializing our all variables.

        noteModalArrayList = new ArrayList<>();
        databaseHelper2 = new DatabaseHelper2(getActivity());

        // getting our course array
        // list from db handler class.
        noteModalArrayList = (ArrayList<Note>) databaseHelper2.getNotes();

        // on below line passing our array list to our adapter class.
        noteRVAdapter = new NoteRVAdapter(noteModalArrayList, getActivity());
        notesRV = view.findViewById(R.id.rv_todayNotes);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        notesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        notesRV.setAdapter(noteRVAdapter);
        return view;
    }
}