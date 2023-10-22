package com.zybooks.loginapp;

import android.util.Log;

public class AuthenticateLogin {

    private static String adminUser = "admin";

    private static String adminPassword = "admin";

    private static String adminSpokenPassword = "this is the administrator pass phrase";

    private static long adminTimeSpoken = 3000;

    private static String newUsername;

    private static String newUserPassword;


    private static String newUserPassPhrase;

    private static long newUserTimeSpoken;

    public boolean validLogin(String username, String password){

        if (username.equals(adminUser) && adminPassword.equals(password)) {
            return true;
        }
        if(newUsername.equals(username) && newUserPassword.equals(password)){
            return true;
        }

        return false;
    }

    public boolean validLogin(String spokenPassword, long timeSpoken){
        if (spokenPassword.equals(newUserPassPhrase) ){
            if ((timeSpoken <= (adminTimeSpoken * 2)) && (timeSpoken >= (adminTimeSpoken * 0.5)))
            return true;
        }

        if (spokenPassword.equals(newUserPassPhrase) ){
            if ((timeSpoken <= (newUserTimeSpoken * 2)) && (timeSpoken >= (newUserTimeSpoken * 0.5)))
                return true;
        }
        return false;
    }

    public void setNewUserTimeSpoken(long timeSpoken){
        newUserTimeSpoken = timeSpoken;
    }

    public void setNewUserPassPhrase(String passPhrase){
        newUserPassPhrase = passPhrase;
    }

    public void setNewUsername(String newUsername) {
        AuthenticateLogin.newUsername = newUsername;
    }

    public void setNewUserPassword(String newUserPassword) {
        AuthenticateLogin.newUserPassword = newUserPassword;
    }
}
