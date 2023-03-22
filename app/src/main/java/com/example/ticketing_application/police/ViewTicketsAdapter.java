package com.example.ticketing_application.police;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing_application.R;
import com.example.ticketing_application.appbusiness.Offense;
import com.example.ticketing_application.appbusiness.Ticket;
import com.example.ticketing_application.database.ITicketDAO;
import com.example.ticketing_application.database.TicketDAO;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ViewTicketsAdapter extends RecyclerView.Adapter<ViewTicketsAdapter.TicketViewHolder> {

    public static ArrayList<Ticket> mTickets;
    public static FragmentManager mFragmentActivity;

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        public ImageView mTicketImage;
        public TextView mTicketOffense;
        public TextView mTicketFine;
        public TextView mIssuerCNIC;
        public TextView mReceiverCNIC;
        public TextView mIsPaid;



        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            mTicketImage = itemView.findViewById(R.id.imageView_ticketImage);
            mTicketOffense = itemView.findViewById(R.id.txtView_Offense);
            mTicketFine = itemView.findViewById(R.id.txtView_Fine);
            mIssuerCNIC = itemView.findViewById(R.id.txtView_IssuedBy);
            mReceiverCNIC = itemView.findViewById(R.id.txtView_IssuedTo);
            mIsPaid = itemView.findViewById(R.id.txtView_isPaid);

            MaterialCardView cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        Offense.getOffenses();
                        return;
                    }
                    else {
                        Ticket ticketInstance = ViewTicketsAdapter.mTickets.get(getAdapterPosition());

                        EditTicketFragment editTicketFragment = new EditTicketFragment(ticketInstance);
                        ViewTicketsAdapter.mFragmentActivity.beginTransaction().replace(R.id.container_main, editTicketFragment).commit();
                    }
                }
            });


        }
    }

    public ViewTicketsAdapter (ArrayList<Ticket> tickets) {
        mTickets = tickets;
    }

    public ViewTicketsAdapter (int keyCNIC, FragmentManager fragmentActivity) {
        mTickets = Ticket.issuerGetIssuedTickets(keyCNIC);
        mFragmentActivity = fragmentActivity;
    }
    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        TicketViewHolder cvh = new TicketViewHolder(v);
        return cvh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket currentTicket = mTickets.get(position);

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
