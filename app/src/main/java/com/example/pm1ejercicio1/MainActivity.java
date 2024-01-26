package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   EditText nombres,apellidos,telefono;
   Button btnproceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres=(EditText) findViewById(R.id.nombres);
        apellidos=(EditText)findViewById(R.id.apellidos);
        telefono=(EditText)findViewById(R.id.telefono);
        btnproceso=(Button) findViewById(R.id.btnprocesar);


        btnproceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle enviaDatos=new Bundle();
                enviaDatos.putString("dato1",nombres.getText().toString());  // aqui guardamos el nombre en el paquete usando la  etiqueta "dato1 y asi para las demas".
                enviaDatos.putString("dato2",apellidos.getText().toString());
                enviaDatos.putString("dato3",telefono.getText().toString());
                // Crear un "Intent" para indicar que queremos ir siguiente pantalla (ActivityPage)
                Intent  intent=new Intent(MainActivity.this, ActivityPage.class);
                intent.putExtras(enviaDatos);// nos sirve para poder enviar los datos  desde una actividad de origen a una actividad de destino
                startActivity(intent); //iniciamos la nueva pantalla


            }

        });
       AddPerson();
    }

    private void AddPerson() {

    }


}