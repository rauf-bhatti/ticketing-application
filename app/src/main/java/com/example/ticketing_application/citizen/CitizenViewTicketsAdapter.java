package com.example.ticketing_application.citizen;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing_application.R;
import com.example.ticketing_application.appbusiness.Offense;
import com.example.ticketing_application.appbusiness.Ticket;
import com.example.ticketing_application.police.EditTicketFragment;
import com.example.ticketing_application.police.ViewTicketsAdapter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CitizenViewTicketsAdapter extends RecyclerView.Adapter<CitizenViewTicketsAdapter.TicketViewHolder> {

    public static ArrayList<Ticket> mTickets;

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        public ImageView mTicketImage;
        public TextView mTicketOffense;
        public TextView mTicketFine;
        public TextView mIssuerCNIC;
        public TextView mReceiverCNIC;
        public TextView mIsPaid;
        public TextView mTicketID;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            mTicketID = itemView.findViewById(R.id.textView_ticketID);
            mTicketImage = itemView.findViewById(R.id.imageView_ticketImage);
            mTicketOffense = itemView.findViewById(R.id.txtView_Offense);
            mTicketFine = itemView.findViewById(R.id.txtView_Fine);
            mIssuerCNIC = itemView.findViewById(R.id.txtView_IssuedBy);
            mReceiverCNIC = itemView.findViewById(R.id.txtView_IssuedTo);
            mIsPaid = itemView.findViewById(R.id.txtView_isPaid);

            Button payTicket = itemView.findViewById(R.id.btn_pay);
            payTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ticket ticketInstance = null;
                    for (int i = 0; i < mTickets.size(); i++) {
                        if (mTickets.get(i).getTicketID() == Integer.parseInt(mTicketID.getText().toString())) {
                            ticketInstance = mTickets.get(i);
                        }
                    }

                    if (ticketInstance != null) {
                        if (ticketInstance.getIsPaid()) {
                            Toast.makeText(view.getContext(), "Ticket is already paid!", Toast.LENGTH_LONG).show();
                        } else {
                            ticketInstance.markTicketPaid();
                            if (Ticket.updateTicket(ticketInstance, ticketInstance.getTicketID()) == 1) {
                                Toast.makeText(view.getContext(), "Ticket has been paid. Thank you!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            });

        }
    }

    public CitizenViewTicketsAdapter (ArrayList<Ticket> tickets) {
        mTickets = tickets;
    }

    public CitizenViewTicketsAdapter (int CNIC) {
        mTickets = Ticket.receiverGetIssuedTickets(CNIC);
    }

    @NonNull
    @Override
    public CitizenViewTicketsAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.citizen_ticket_item, parent, false);
        CitizenViewTicketsAdapter.TicketViewHolder cvh = new CitizenViewTicketsAdapter.TicketViewHolder(v);
        return cvh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CitizenViewTicketsAdapter.TicketViewHolder holder, int position) {
        Ticket currentTicket = mTickets.get(position);

        holder.mTicketID.setText(String.valueOf(currentTicket.getTicketID()));
        holder.mTicketImage.setImageResource(R.drawable.nhmp_logo);
        holder.mTicketOffense.setText("Offense: " + currentTicket.getTicketOffense());
        holder.mTicketFine.setText("Fine: " + String.valueOf(currentTicket.getFine()));
        holder.mIssuerCNIC.setText("Issuer: " + String.valueOf(currentTicket.getIssuerCNIC()));
        holder.mReceiverCNIC.setText("Receiver: " + String.valueOf(currentTicket.getReceiverCNIC()));
        holder.mIsPaid.setText("Paid: " + String.valueOf(currentTicket.getIsPaid()));
    }

    @Override
    public int getItemCount() {
        if (mTickets != null) {
            return mTickets.size();
        }
        else {
            return 0;
        }
    }
}
