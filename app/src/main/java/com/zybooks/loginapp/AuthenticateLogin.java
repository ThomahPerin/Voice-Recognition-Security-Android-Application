package com.zybooks.loginapp;

import android.util.Log;

public class AuthenticateLogin {

    private static String adminUser = "admin";

    private static String adminPassword = "admin";

    public boolean validLogin(String username, String password){
        Log.i(username + password, "Input");
        if (username.equals(adminUser) && adminPassword.equals(password)) {
            return true;
        }
        return false;
    }
}
