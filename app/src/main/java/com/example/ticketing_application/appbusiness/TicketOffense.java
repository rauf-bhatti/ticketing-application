package com.example.ticketing_application.appbusiness;

import java.util.ArrayList;

public class TicketOffense {
    private int mOffenseID;
    private String mOffenseType;

    public TicketOffense (int offenseID, String offenseType) {
        mOffenseID = offenseID;
        mOffenseType = offenseType;
    }

    public static ArrayList<TicketOffense> getOffenses() {

        return null;
    }
}
