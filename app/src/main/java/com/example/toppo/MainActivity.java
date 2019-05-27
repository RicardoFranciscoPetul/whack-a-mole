package com.example.toppo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play, records;
    TextView tv_error;
    EditText txt_user;
    String user ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        records = findViewById(R.id.records);
        txt_user = findViewById(R.id.txt_user);
        tv_error = findViewById(R.id.tv_error);
        txt_user.setText("");
    }

    public void Listener(View v) {
        if (v.getId() == R.id.play && txt_user.getText().length()==0) {
            tv_error.setVisibility(View.VISIBLE);
            txt_user.requestFocus();
            return;
        }else if (v.getId()==R.id.play){
            Play();
            tv_error.setVisibility(View.INVISIBLE);
            return;
        }
        Records();
    }

    private void Records() {
        Intent records = new Intent(this, Records.class);
        startActivity(records);
    }

    private void Play() {
        user = txt_user.getText().toString();
        Intent play = new Intent(this, Game.class);
        play.putExtra("userName", user);
        startActivity(play);
    }
}
