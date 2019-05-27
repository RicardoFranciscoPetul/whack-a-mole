package com.example.toppo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Records extends AppCompatActivity {

    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        lv = findViewById(R.id.lista);

        consultar();
    }

    private void consultar() {
        SQL sql = new SQL(this, "gameRecords", null, 1);
        lista = sql.llenar();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);
    }
}
