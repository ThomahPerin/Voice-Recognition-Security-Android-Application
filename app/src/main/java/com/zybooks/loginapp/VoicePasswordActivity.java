package com.zybooks.loginapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class VoicePasswordActivity extends AppCompatActivity {

    private Button recording;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_password);

        recording = findViewById(R.id.recordButton);
    }

    public void recording(){

    }
}
