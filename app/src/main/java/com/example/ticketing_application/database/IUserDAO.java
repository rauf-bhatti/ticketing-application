package com.example.ticketing_application.database;

public interface IUserDAO {
    public int checkLogin(String username, String password);
}
