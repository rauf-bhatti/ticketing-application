package com.example.ticketing_application.database;

import java.sql.ResultSet;

public class UserDAO implements IUserDAO{
    private CloudDAL cloudDAL = new CloudDAL();
    @Override
    public int checkLogin(String username, String password) {
        try {
            String query = "SELECT * FROM ProgramUsers WHERE cnic = '" + username + "'" + "AND user_password = '" + password + "';";
            ResultSet resultSet = cloudDAL.get(query);

            if (resultSet == null) {
                return -1; //Meaning error!
            }
            else {
                if (resultSet.next()) {
                    return resultSet.getInt(3); //The access level! Return the access level on successful login.
                }
                return -1;
            }
        }
        catch (Exception exception) {
            return -1;
        }
    }
}
