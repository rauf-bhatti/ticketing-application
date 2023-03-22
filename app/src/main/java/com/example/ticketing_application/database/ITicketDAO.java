package com.example.ticketing_application.database;

import com.example.ticketing_application.appbusiness.Ticket;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ITicketDAO {
    public ArrayList<Ticket> getReceivedTickets(int keyCNIC);
    public ArrayList<Ticket> getIssuedTickets(int keyCNIC);
    public boolean issueTicket(Ticket ticket);
    public boolean updateTicket(Ticket ticket, int key);

}
