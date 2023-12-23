package com.example.datesreminderapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.datesreminderapp.models.Agent;
import com.example.datesreminderapp.models.Note;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDateFragment extends Fragment {

    private Button btnDate;
    private Button btnTime;
    private Button btnReminder;
    private Button btnSaveNote;

    private EditText et_noteTitle;
    private EditText et_noteDescription;
    private String store_noteDate;

    private String store_noteTime;
    private EditText et_noteAgentName;

    private EditText et_noteAgentNumber;

    private EditText et_noteDependencyName;

    private String store_noteReminder;



    String timeStorage;

    public AddDateFragment() {
        // Required empty public constructor
    }

    public static AddDateFragment newInstance(String param1, String param2) {
        AddDateFragment fragment = new AddDateFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public String formatTimeHelper(int hour, int minute) {                                                //this method converts the time into 12hr format and assigns am or pm

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }
    private void handleSelectDate() {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), R.style.DatePickerTheme,  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btnDate.setText(day + "-" + (month + 1) + "-" + year);
                store_noteDate = day + "-" + (month + 1) + "-" + year;
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void handleSelectTime() {                                                                     //this method performs the time picker task
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), R.style.DatePickerTheme,  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeStorage = i + ":" + i1;                                                        //temp variable to store the time to set alarm
                btnTime.setText(formatTimeHelper(i, i1));                                                //sets the button text as selected time
                store_noteTime = formatTimeHelper(i, i1);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
    private void handleSelectReminder() {                                                                     //this method performs the time picker task
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), R.style.DatePickerTheme,  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeStorage = i + ":" + i1;                                                        //temp variable to store the time to set alarm
                btnReminder.setText(formatTimeHelper(i, i1));                                                //sets the button text as selected time
                store_noteReminder = formatTimeHelper(i, i1);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_date, container, false);
        btnDate =  view.findViewById(R.id.btn_date);                                             //assigned all the material reference to get and set data
        btnTime =  view.findViewById(R.id.btn_time);
        btnReminder = view.findViewById(R.id.btn_reminder);
        btnSaveNote = view.findViewById(R.id.btn_saveNote);

        et_noteTitle = view.findViewById(R.id.et_noteTitle);
        et_noteDescription   = view.findViewById(R.id.et_noteDescription);
        // TODO : get time and date from the buttons
        et_noteAgentName   = view.findViewById(R.id.et_agentName);
        et_noteAgentNumber   = view.findViewById(R.id.et_agentNumber);
        et_noteDependencyName   = view.findViewById(R.id.et_dependencyName);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleSelectDate();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelectTime();

            }
        });
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelectReminder();

            }
        });

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String title = et_noteTitle.getText().toString();
                    String description = et_noteDescription.getText().toString();
                    String agentName = et_noteAgentName.getText().toString();
                    String agentNumber = et_noteAgentNumber.getText().toString();
                    String dependencyName = et_noteDependencyName.getText().toString();
                    String time = store_noteTime;

                    String date = store_noteDate;
                    String reminder = store_noteReminder;
                    boolean isUpdated = false;
                    String status = "todo";

                    Note note = new Note(-1, title , description, date , time , reminder , agentName , agentNumber , dependencyName , isUpdated , status);
                    DatabaseHelper2 dbHelper = new DatabaseHelper2(getActivity());
                    boolean insertSuccessful = dbHelper.insertNote(note);

                    if (insertSuccessful) {
                        Toast.makeText(getActivity(), "Note added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to add note", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(getActivity(), "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}