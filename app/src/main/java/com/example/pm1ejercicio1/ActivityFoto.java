package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityFoto extends AppCompatActivity {
  static final int permiso_camara=100;
  static final  int peticion_foto=102;

  String fotopath;
  ImageView imageView;
  Button btntomarfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        imageView=findViewById(R.id.imageView);
        btntomarfoto=findViewById(R.id.btntomarfoto);


      btntomarfoto.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              permisos();
          }
      });


    }

    private void permisos() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},permiso_camara);
        }
        else{
            tomarfoto();
        }
    }

    private void tomarfoto() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //intencion de tomar fotografia
        if(intent.resolveActivity(getPackageManager())!=null)
            startActivityForResult(intent,peticion_foto);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if(requestCode==permiso_camara){
           if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
               tomarfoto();
           }
           else{

               Toast.makeText(getApplicationContext(),"PERMISO DENEGADO",Toast.LENGTH_SHORT).show();

           }
       }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==peticion_foto && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            Bitmap imagen=(Bitmap) extras.get("data");
            imageView.setImageBitmap(imagen);

        }
    }
}