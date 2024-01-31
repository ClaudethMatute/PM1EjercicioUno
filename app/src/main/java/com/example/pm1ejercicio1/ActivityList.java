package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pm1ejercicio1.Models.Personas;
import com.example.pm1ejercicio1.configuracion.SQLiteConexion;
import com.example.pm1ejercicio1.configuracion.Transaciones;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listpersonas;
   ArrayList<Personas> Lista;
   ArrayList<String>Arreglo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

       conexion = new SQLiteConexion(this, Transaciones.DBName, null, 1); //amara o castea las clases
       listpersonas = (ListView) findViewById(R.id.listpersonas);
 //obtenr la informacion
       ObtenerInfo();
       //custor adater la forma de llenar la infromacion dentro de un objet fisico
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,Arreglo);
        listpersonas.setAdapter(adp);

    }

    private void ObtenerInfo() {
        SQLiteDatabase db=conexion.getReadableDatabase();
        Personas person=null;
        Lista=new  ArrayList<Personas>();

        // Cursor de base de datos para recorrer los datos

        Cursor cursor = db.rawQuery(Transaciones.SelectAllPersonas, null);


        //recorer el cusor
        while(cursor.moveToNext())
        {
            person = new Personas();
            person.setId(cursor.getInt(0));
            person.setNombre(cursor.getString(1));
            person.setApellidos(cursor.getString(2));
            person.setEdad(cursor.getInt(3));
            person.setCorreo(cursor.getString(4));

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