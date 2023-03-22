package com.example.ticketing_application.police;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ticketing_application.R;
import com.example.ticketing_application.appbusiness.Ticket;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PoliceViewTicketsActivity extends AppCompatActivity {

    private Button btnIssueTicket;
    private RecyclerView mRecyclerView;
    public ViewTicketsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mCNIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_view_tickets);

        btnIssueTicket = (Button) findViewById(R.id.btn_issueTicket);

        btnIssueTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToIssueTicket();
            }
        });

        mCNIC = getIntent().getIntExtra("USER_ID", mCNIC);

        configureRecyclerView();
    }



    private void configureRecyclerView() {
        /*ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));*/

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_viewTickets);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ViewTicketsAdapter(mCNIC, getSupportFragmentManager());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void changeToIssueTicket() {
        Intent intent = new Intent(this, IssueTicketActivity.class);
        intent.putExtra("USER_ID", mCNIC);
        startActivity(intent);
    }

    private void onOrientationChangedPotrait() {
        configureRecyclerView();
        btnIssueTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToIssueTicket();
            }
        });

    }

    private void onOrientationChangedLandscape() {
/*
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout_main);
        frameLayout.addView(new Button(this));
*/
        configureRecyclerView();
       // bindEditFragment();
        //Add Fragment
    }

    private void bindEditFragment() {
        Fragment editTicketFragment = new EditTicketFragment();
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.container_main, editTicketFragment);
        replace.commit();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_police_view_tickets);
            onOrientationChangedLandscape();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_police_view_tickets);
            btnIssueTicket = (Button)findViewById(R.id.btn_issueTicket);
            onOrientationChangedPotrait();
        }
    }
}