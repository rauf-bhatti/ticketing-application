package com.example.ticketing_application.appbusiness;

import com.example.ticketing_application.database.JSONImporter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Offense {
    //private final ArrayList<Offenses> offensesList = new ArrayList<>();
    private static JSONImporter jsonImporter = new JSONImporter();

    private String mOffenseType;

    public Offense(String offenseType) {
        mOffenseType = offenseType;
    }

    public Offense () { }

    public String getOffense() {
        return mOffenseType;
    }

    public static ArrayList<String> getOffenses() {
        StringBuffer violationsJSON = jsonImporter.getViolationsJSON();
        ArrayList<String> offenses = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(violationsJSON.toString());
            JSONArray violationsArr = jsonObject.getJSONArray("offenses");
            for (int i = 0; i < violationsArr.length(); i++) {
                JSONObject innerObject = violationsArr.getJSONObject(i);
                offenses.add(innerObject.getString("offenseName"));
            }

            return offenses;
        }
        catch (Exception exception) {
            return null;
        }
    }

   /* public static String [] GetViolationsString() {
        String [] violationString;
        ArrayList<Offense> list = getOffenses();

        for (int i = 0; i < list.size(); i++) {
        }

    }*/

}
