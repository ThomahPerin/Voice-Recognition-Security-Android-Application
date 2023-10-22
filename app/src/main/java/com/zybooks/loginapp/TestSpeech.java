package com.zybooks.loginapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TestSpeech extends AppCompatActivity {

    //TextView textView;

    private String spokenPassword;

    private AuthenticateLogin spoken;

    private long startTime;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testspeech);

        //textView = findViewById(R.id.textView);
        spoken = new AuthenticateLogin();
        startTime = 0;

    }

    public void speak(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start Speaking");
        startTime = System.currentTimeMillis();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            //textView.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
            spokenPassword = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
            long endTime = System.currentTimeMillis();
            long speakingDurationMillis = endTime - startTime;

            boolean validPassword = spoken.validLogin(spokenPassword, speakingDurationMillis);

            if (validPassword){
                Intent proceed = new Intent(this, StoreData.class);
                startActivity(proceed);
            }
            else{
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Login Not Approved")
                        .setMessage("Your login wasn't approved")
                        .setCancelable(true)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .show();

                Log.i("Invalid", "Invalid");
            }
            Log.i(spokenPassword, spokenPassword);
        }
    }
}
