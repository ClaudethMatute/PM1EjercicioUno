package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import com.example.pm1ejercicio1.configuracion.Transaciones;
import com.example.pm1ejercicio1.configuracion.SQLiteConexion;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nombres, apellidos, edad,correo;
    Button btnproceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        btnproceso = (Button) findViewById(R.id.btnprocesar);


        btnproceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Bundle enviaDatos = new Bundle();
              //  enviaDatos.putString("dato1", nombres.getText().toString());  // aqui guardamos el nombre en el paquete usando la  etiqueta "dato1 y asi para las demas".
              //  enviaDatos.putString("dato2", apellidos.getText().toString());
              //  enviaDatos.putString("dato3", edad.getText().toString());
              //  enviaDatos.putString("dato4", correo.getText().toString());
                // Crear un "Intent" para indicar que queremos ir siguiente pantalla (ActivityPage)
               // Intent intent = new Intent(MainActivity.this, ActivityPage.class);
              //  intent.putExtras(enviaDatos);// nos sirve para poder enviar los datos  desde una actividad de origen a una actividad de destino
              //  startActivity(intent); //iniciamos la nueva pantalla
                AddPerson();
            }
        });

    }
    private void AddPerson() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transaciones.DBName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase(); //modo de escritura

        ContentValues valores = new ContentValues();
        valores.put(Transaciones.nombre, nombres.getText().toString());
        valores.put(Transaciones.apellidos, apellidos.getText().toString());
        valores.put(Transaciones.edad, edad.getText().toString());
        valores.put(Transaciones.correo, correo.getText().toString());


        // Insertar datos en la tabla
        Long resultado = db.insert(Transaciones.Tablepersonas, Transaciones.id, valores);

        //  insertar

        Toast.makeText(getApplicationContext(), "Registro Ingresado Correctamente"+ resultado.toString(),
                Toast.LENGTH_SHORT).show();

        db.close();
        // Limpiar los EditText después de la inserción
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        // Regresar al Activity Dash
        finish();
    }

}
