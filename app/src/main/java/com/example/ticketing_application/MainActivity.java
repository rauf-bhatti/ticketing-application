package com.example.ticketing_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ticketing_application.appbusiness.User;
import com.example.ticketing_application.citizen.CitizenViewTickets;
import com.example.ticketing_application.police.PoliceViewTicketsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_Username = findViewById(R.id.editText_ID);
        EditText editText_Password = findViewById(R.id.editText_Password);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!editText_Password.getText().toString().isEmpty() && !editText_Username.getText().toString().isEmpty()) {
                        int accessLevel = User.checkLogin(editText_Username.getText().toString(), editText_Password.getText().toString());

                        if (accessLevel == 1) {
                            citizenActivity(Integer.parseInt(editText_Username.getText().toString()));
                            Toast.makeText(view.getContext(), "Logged in! Welcome " + editText_Username.getText().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            adminActivity(Integer.parseInt(editText_Username.getText().toString()));
                            Toast.makeText(view.getContext(), "Logged in! Welcome " + editText_Username.getText().toString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Please fill in the fields before pressing on login!", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception exception) {
                    Toast.makeText(view.getContext(), "Only numbers are allowed in username!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void citizenActivity(int CNIC) {
        Intent intent = new Intent(this, CitizenViewTickets.class);
        intent.putExtra("USER_ID", CNIC);
        startActivity(intent);
    }

    private void adminActivity(int CNIC) {
        Intent intent = new Intent(this, PoliceViewTicketsActivity.class);
        intent.putExtra("USER_ID", CNIC);
        startActivity(intent);
    }
}