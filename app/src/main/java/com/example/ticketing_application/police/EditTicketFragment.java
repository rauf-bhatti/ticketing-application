package com.example.ticketing_application.police;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ticketing_application.R;
import com.example.ticketing_application.appbusiness.Offense;
import com.example.ticketing_application.appbusiness.Ticket;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditTicketFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText mCitizenID;
    private EditText mFine;
    private Spinner mOffense;
    private Ticket mTicket;

    //Buttons go here
    private Button mUpdateButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditTicketFragment() {
        // Required empty public constructor
    }

    public EditTicketFragment(Ticket ticket) {
        // Required empty public constructor
        mTicket = ticket;
    }


    @Override
    public void onStart() {
        super.onStart();
        mCitizenID.setText(String.valueOf(mTicket.getReceiverCNIC()));
        mFine.setText(String.valueOf(mTicket.getFine()));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Offense.getOffenses());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mOffense.setAdapter(adapter);




        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Update_Ticket", "Update button pressed!");

                try {
                    if (!mCitizenID.getText().toString().isEmpty() && !mFine.getText().toString().isEmpty()) {
                        int fine = Integer.parseInt(mFine.getText().toString());
                        int citizenID = Integer.parseInt(mCitizenID.getText().toString());
                        String offence = mOffense.getSelectedItem().toString();

                        Ticket updatedTicket = new Ticket(mTicket.getTicketID(), citizenID, mTicket.getReceiverCNIC(), mTicket.getIsPaid(),
                                mTicket.getIsActive(), fine, offence);

                       int result = Ticket.updateTicket(updatedTicket, mTicket.getTicketID());

                       if (result == 1) {
                           Toast.makeText(view.getContext(), "Record updated!", Toast.LENGTH_LONG).show();
                       }
                       else if (result == 0){
                           Toast.makeText(view.getContext(), "Record could not be updated!", Toast.LENGTH_LONG).show();
                       }
                       else {
                           Toast.makeText(view.getContext(), "Exception encountered!", Toast.LENGTH_LONG).show();
                       }
                    }
                }
                catch (Exception exception) {
                    Toast.makeText(view.getContext(), "Please recheck your fields!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditTicketFragment newInstance(String param1, String param2) {
        EditTicketFragment fragment = new EditTicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_ticket, container, false);
        mCitizenID =  view.findViewById(R.id.editText_frag_userID);
        mFine = view.findViewById(R.id.editText_frag_Fine);
        mUpdateButton = view.findViewById(R.id.btn_update);
        mOffense = view.findViewById(R.id.spinner_frag_Offence);

        return view;
    }
}