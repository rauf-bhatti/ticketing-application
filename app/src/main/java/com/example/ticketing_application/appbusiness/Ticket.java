package com.example.ticketing_application.appbusiness;

import android.util.Log;

import com.example.ticketing_application.database.ITicketDAO;
import com.example.ticketing_application.database.TicketDAO;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Ticket {
    private int mTicketID;
    private int mIssuerCNIC;
    private int mReceiverCNIC;
    private boolean mIsPaid;
    private boolean mIsActive;
    private int mFine;
    private String mOffenseType;

    private static ITicketDAO mTicketDAO = new TicketDAO();

    public Ticket (int ticketID, int issuerCNIC, int receiverCNIC, boolean isPaid, boolean isActive, int fine, String offenseType) {
        mTicketID = ticketID;
        mIssuerCNIC = issuerCNIC;
        mReceiverCNIC = receiverCNIC;
        mIsPaid = isPaid;
        mIsActive = isActive;
        mFine = fine;
        mOffenseType = offenseType;
    }

    public Ticket (int issuerCNIC, int receiverCNIC, boolean isPaid, boolean isActive, int fine, String offenseType) {
        mIssuerCNIC = issuerCNIC;
        mReceiverCNIC = receiverCNIC;
        mIsPaid = isPaid;
        mIsActive = isActive;
        mFine = fine;
        mOffenseType = offenseType;
    }

    public static ArrayList<Ticket> receiverGetIssuedTickets(int keyCNIC) {
        return mTicketDAO.getReceivedTickets(keyCNIC);
    }

    public static ArrayList<Ticket> issuerGetIssuedTickets(int keyCNIC) {
        return mTicketDAO.getIssuedTickets(keyCNIC);
    }

    public static int issueTicket(Ticket ticket) {
        try {
            if (mTicketDAO != null) {
                if (mTicketDAO.issueTicket(ticket)) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                return -1;
            }
        }
        catch (Exception exception) {
            Log.e("Ticket_Class", exception.getMessage());
            return -1;
        }
    }

    public static int updateTicket(Ticket ticket, int key) {
        try {
            if (mTicketDAO != null) {
                if (mTicketDAO.updateTicket(ticket, key)) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                return -1;
            }
        }
        catch (Exception exception) {
            Log.e("Ticket_Class", exception.getMessage());
            return -1;
        }
    }

    public int getIssuerCNIC() { return mIssuerCNIC; }
    public int getReceiverCNIC() { return mReceiverCNIC; }
    public boolean getIsPaid() { return mIsPaid; }
    public boolean getIsActive() { return mIsActive; }
    public int getFine() { return mFine; }
    public String getTicketOffense() { return mOffenseType; }
    public int getTicketID() { return mTicketID; }
    public void markTicketPaid() { mIsPaid = true; }
}
