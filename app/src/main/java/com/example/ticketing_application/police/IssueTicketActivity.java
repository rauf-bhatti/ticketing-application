package com.example.ticketing_application.police;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ticketing_application.R;
import com.example.ticketing_application.appbusiness.Offense;
import com.example.ticketing_application.appbusiness.Ticket;
import com.example.ticketing_application.database.JSONImporter;

public class IssueTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_ticket);

        EditText editText_CNIC = (EditText) findViewById(R.id.editText_userID);
        EditText editText_Fine = (EditText) findViewById(R.id.editText_Fine);
        Spinner spinner_Offense = (Spinner) findViewById(R.id.spinner_Offence);


        //Initialize spinner here
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, Offense.getOffenses());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Offense.setAdapter(adapter);

        int mCNIC = 0;
        mCNIC = getIntent().getIntExtra("USER_ID", mCNIC);



        Log.e("ACT_CRETED", "Shit just got created...");

        Button btnSubmitTicket = (Button) findViewById(R.id.btn_submitTicket);
        int finalMCNIC = mCNIC;
        btnSubmitTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String CNIC = editText_CNIC.getText().toString();
                    String fine = editText_Fine.getText().toString();
                    String offence = spinner_Offense.getSelectedItem().toString();

                    if (CNIC.isEmpty() || fine.isEmpty()) {
                        Toast.makeText(view.getContext(), "Fields were left empty!", Toast.LENGTH_LONG).show();
                    }
                    else {

                        int i_CNIC = Integer.parseInt(CNIC);
                        int i_fine = Integer.parseInt(fine);

                        Log.e("Ticket_Inputs", (CNIC + fine));

                        int result = Ticket.issueTicket(new Ticket(finalMCNIC, i_CNIC, false, true, i_fine, offence));

                        Toast.makeText(view.getContext(), "Record inserted!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(view.getContext(), PoliceViewTicketsActivity.class);
                        intent.putExtra("USER_ID", finalMCNIC);
                        startActivity(intent);

                    }
                }
                catch (Exception exception) {
                    Toast.makeText(view.getContext(), "Please recheck your inputs!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}