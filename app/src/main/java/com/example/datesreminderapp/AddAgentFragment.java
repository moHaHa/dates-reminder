package com.example.datesreminderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datesreminderapp.models.Agent;


public class AddAgentFragment extends Fragment {


    private Button btn_addAgent;
    private EditText et_agentName;
    private EditText et_agentNumber;
    private EditText et_agentDescription;
    public AddAgentFragment() {
        // Required empty public constructor
    }

    public static AddAgentFragment newInstance(String param1, String param2) {
        AddAgentFragment fragment = new AddAgentFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_agent, container, false);

        btn_addAgent = view.findViewById(R.id.btn_saveAgent);
        et_agentName = view.findViewById(R.id.et_agentName);
        et_agentNumber = view.findViewById(R.id.et_agentNumber);
        et_agentDescription = view.findViewById(R.id.et_agentDescription);
        /**
         * Handle On Add Agent Event
         * with success message, and  error handling
         */
        btn_addAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = et_agentName.getText().toString();
                    String number = et_agentNumber.getText().toString();
                    String description = et_agentDescription.getText().toString();

                    Agent agent = new Agent(-1, name, number, description);
                    DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                    boolean insertSuccessful = dbHelper.insertAgent(agent);

                    if (insertSuccessful) {
                        Toast.makeText(getActivity(), "Agent added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to add agent", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}