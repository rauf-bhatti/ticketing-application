package com.example.ticketing_application.citizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ticketing_application.R;
import com.example.ticketing_application.appbusiness.Ticket;
import com.example.ticketing_application.police.ViewTicketsAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class CitizenViewTickets extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public CitizenViewTicketsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_view_tickets);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        int CNIC = 0;
        CNIC = getIntent().getIntExtra("USER_ID", CNIC);

        configureRecyclerView(CNIC);
    }

    private void configureRecyclerView(int CNIC) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));
        tickets.add(new Ticket(1, 1, 1, true, true, 500, "Hello"));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_viewTickets);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CitizenViewTicketsAdapter(CNIC);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}