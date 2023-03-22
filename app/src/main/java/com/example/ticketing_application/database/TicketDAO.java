package com.example.ticketing_application.database;

import android.content.ContentValues;

import com.example.ticketing_application.appbusiness.Ticket;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TicketDAO implements ITicketDAO {
    private final CloudDAL mCloudDAL = new CloudDAL();

    @Override
    public ArrayList<Ticket> getReceivedTickets(int keyCNIC) {
        ArrayList<Ticket> issuedTickets = new ArrayList<>();

        try {
            ResultSet resultSet = mCloudDAL.get("SELECT * FROM Tickets WHERE receiverCNIC = '" + keyCNIC + "'");

            if (resultSet != null) {
                while (resultSet.next()) {
                    issuedTickets.add(new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                            resultSet.getBoolean(4), resultSet.getBoolean(5),
                            resultSet.getInt(6), resultSet.getString(7)));
                }

                return issuedTickets;
            }

            return null;
        }
        catch (Exception exception) {
            return null;
        }
    }

    @Override
    public ArrayList<Ticket> getIssuedTickets(int keyCNIC) {
        ArrayList<Ticket> issuedTickets = new ArrayList<>();

        try {
            ResultSet resultSet = mCloudDAL.get("SELECT * FROM Tickets WHERE issuerCNIC = '" + keyCNIC + "'");

            if (resultSet != null) {
                while (resultSet.next()) {
                    issuedTickets.add(new Ticket(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
                            resultSet.getBoolean(4), resultSet.getBoolean(5),
                            resultSet.getInt(6), resultSet.getString(7)));
                }

                return issuedTickets;
            }

            return null;
        }
        catch (Exception exception) {
            return null;
        }
    }

    @Override
    public boolean issueTicket(Ticket ticket) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("issuerCNIC", ticket.getIssuerCNIC());
        contentValues.put("receiverCNIC", ticket.getReceiverCNIC());
        contentValues.put("isPaid", ticket.getIsPaid());
        contentValues.put("isActive", ticket.getIsActive());
        contentValues.put("fine", ticket.getFine());
        contentValues.put("offenseType", ticket.getTicketOffense());

        return mCloudDAL.insert(contentValues, "Tickets");
    }

    @Override
    public boolean updateTicket(Ticket ticket, int key) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("issuerCNIC", ticket.getIssuerCNIC());
        contentValues.put("receiverCNIC", ticket.getReceiverCNIC());
        contentValues.put("isPaid", ticket.getIsPaid());
        contentValues.put("isActive", ticket.getIsActive());
        contentValues.put("fine", ticket.getFine());
        contentValues.put("offenseType", ticket.getTicketOffense());

        return mCloudDAL.update("Tickets", "ticketID", key, contentValues);
    }



}
