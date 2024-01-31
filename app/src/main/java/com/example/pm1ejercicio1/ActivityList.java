package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.pm1ejercicio1.configuracion.SQLiteConexion;

public class ActivityList extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listpersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

    }
}