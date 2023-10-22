package com.zybooks.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;

    private Button createAccount;
    private String inputUsername;
    private String inputPassword;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);
    }

    public void login(View v){
        Intent record = new Intent(this, TestSpeech.class);
       inputUsername = username.getText().toString();
       inputPassword = password.getText().toString();

       AuthenticateLogin login = new AuthenticateLogin();
       boolean valid = login.validLogin(inputUsername, inputPassword);
       if (valid == true){
           Log.i("True", "Returned True");
           builder = new AlertDialog.Builder(this);
           builder.setTitle("Login Approved")
                   .setMessage("Your login was approved.")
                   .setCancelable(true)
                   .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           startActivity(record);
                           Log.i("NEW RECORD", "Record");
                           //finish();
                       }
                   })
                   .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           finish();
                           //dialogInterface.cancel();
                       }
                   })
                   .show();
       }
       else{
           Log.i("False", "Returned False");
           builder = new AlertDialog.Builder(this);
           builder.setTitle("Login Not Approved")
                   .setMessage("Your username or password is incorrect.")
                   .setCancelable(true)
                   .show();
       }
    }

    public void createAccount(View v){
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
    }
}