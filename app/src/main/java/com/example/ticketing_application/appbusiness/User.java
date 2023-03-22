package com.example.ticketing_application.appbusiness;

import com.example.ticketing_application.database.IUserDAO;
import com.example.ticketing_application.database.UserDAO;

public class User {
    private int m_CNIC;
    private String m_Password;
    private int m_accessLevel; //Used to define if the user is officer or citizen
    private static IUserDAO mUserDAO = new UserDAO();

    public User(int CNIC, String Password, int accessLevel) {
        m_CNIC = CNIC;
        m_Password = Password;
        m_accessLevel = accessLevel;
    }

    public int getCNIC() { return m_CNIC; }
    public String getPassword() { return m_Password; }
    public int getAccessLevel() { return m_accessLevel; }
    public static int checkLogin(String username, String password) {
        return mUserDAO.checkLogin(username, password);
    }
}
