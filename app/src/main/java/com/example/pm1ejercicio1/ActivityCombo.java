package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pm1ejercicio1.Models.Personas;
import com.example.pm1ejercicio1.configuracion.SQLiteConexion;
import com.example.pm1ejercicio1.configuracion.Transaciones;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner combopersonas;
    EditText nombres, apellidos, correo;

    ArrayList<Personas> Lista;
    ArrayList<String> Arreglo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transaciones.DBName, null, 1);
        combopersonas = (Spinner) findViewById(R.id.spinner);
        nombres = (EditText) findViewById(R.id.cbnombre);
        apellidos = (EditText) findViewById(R.id.cbapellido);
        correo = (EditText) findViewById(R.id.cbcorreo);

        ObtenerInfo();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                Arreglo);

        combopersonas.setAdapter(adapter);

        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try
                {
                    nombres.setText(Lista.get(position).getNombre());
                    apellidos.setText(Lista.get(position).getApellidos());
                    correo.setText(Lista.get(position).getCorreo());
                }
                catch (Exception ex)
                {
                    ex.toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void ObtenerInfo()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas person = null;
        Lista = new ArrayList<Personas>();

        // Cursor de base de datos para recorrer los datos
        Cursor cursor = db.rawQuery(Transaciones.SelectAllPersonas, null);

        while(cursor.moveToNext())
        {
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombre(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));
            person.setDireccion(cursor.getString(5));

            Lista.add(person);
        }

        cursor.close();

        FillData();
    }

    private void FillData()
    {
        Arreglo = new ArrayList<String>();
        for(int i = 0; i < Lista.size(); i ++)
        {
            Arreglo.add(Lista.get(i).getId() + " - "+
                    Lista.get(i).getNombre()  +" - "+
                    Lista.get(i).getApellidos()) ;
        }
    }
}