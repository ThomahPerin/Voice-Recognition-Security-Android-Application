package com.zybooks.loginapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;

    private AuthenticateLogin newUser;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        newUser = new AuthenticateLogin();
    }

    public void createAccount(View v){

        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        newUser.setNewUsername(inputUsername);
        newUser.setNewUserPassword(inputPassword);
        Intent createAcc = new Intent(this, RecordPassword.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Account Created")
                .setMessage("You now need to record a pass phrase of five or more words.")
                .setCancelable(true)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(createAcc);
                        //finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        //dialogInterface.cancel();
                    }
                })
                .show();

    }
}
