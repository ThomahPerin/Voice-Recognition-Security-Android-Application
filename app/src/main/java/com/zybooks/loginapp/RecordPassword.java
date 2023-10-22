package com.zybooks.loginapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;

public class RecordPassword extends AppCompatActivity {

    private String spokenPassword;

    private AuthenticateLogin spoken;

    private long startTime;

    private long speakingDurationSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_password);

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

        //new shit

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            //textView.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
            spokenPassword = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);

            long endTime = System.currentTimeMillis();
            long speakingDurationMillis = endTime - startTime;
            spoken.setNewUserTimeSpoken(speakingDurationMillis);
            spoken.setNewUserPassPhrase(spokenPassword);

                Intent proceed = new Intent(this, StoreData.class);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pass Phrase Recorded")
                    .setMessage("Your Pass Phrase is: " + spokenPassword)
                    .setCancelable(true)
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            startActivity(proceed);
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


}