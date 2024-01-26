package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityPage extends AppCompatActivity {
 TextView mostrar;
 Button btnregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        mostrar = (TextView) findViewById(R.id.mostrar);
        btnregresar = (Button) findViewById(R.id.btnregresar);

        // Obtener el Bundle de datos
        Bundle recibedatos = getIntent().getExtras();


        if (recibedatos != null) {
            // Obtener los datos individuales del Bundle
            String nombre = recibedatos.getString("dato1");
            String apellidos = recibedatos.getString("dato2");
            String telefono = recibedatos.getString("dato3");


            String textoMostrar = "Nombre: " + nombre + "\nApellidos: " + apellidos + "\nTeléfono: " + telefono;  // una cadena de texto la cual se mostrará en el TextView

            // mostrar en el TextView
            mostrar.setText(textoMostrar);
        }

    btnregresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Crear un Intent para volver a la pantalla principal (MainActivity)
            Intent intentVolver = new Intent(ActivityPage.this, MainActivity.class);

            // Iniciar la actividad principal y cerrar la actual
            startActivity(intentVolver);
            finish();
        }
    });
}
}

