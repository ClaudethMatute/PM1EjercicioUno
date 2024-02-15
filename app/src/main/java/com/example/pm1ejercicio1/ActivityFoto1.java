package com.example.pm1ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityFoto1 extends AppCompatActivity {
    static final int permiso_camara = 100;
    static final int peticion_foto = 102;
    static final int seleccionar_foto = 103; // Nuevo código de solicitud para seleccionar una foto

    String fotopath;
    ImageView imageView;
    Button btntomarfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto1);

        imageView = (ImageView) findViewById(R.id.imageView);
        btntomarfoto = (Button) findViewById(R.id.btntomarfoto);

        btntomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo(); // Llama al método para mostrar el diálogo de selección
            }
        });
    }

    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar una opción")
                .setItems(new CharSequence[]{"Tomar foto", "Seleccionar de la galería"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                permisos(); // Si elige "Tomar foto", solicita permisos y luego toma la foto
                                break;
                            case 1:
                                seleccionarDeGaleria(); // Si elige "Seleccionar de la galería", abre la galería
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void seleccionarDeGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, seleccionar_foto); // Inicia la actividad de selección de la galería con el nuevo código de solicitud
    }


    private void permisos() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, permiso_camara);
        } else {
            tomarfoto(); // Si ya tiene permisos, toma la foto
        }
    }

    private void tomarfoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // intencion de tomar fotografia
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, peticion_foto); // Inicia la actividad de tomar foto con el código de solicitud existente
    }

    private void guardarFotoEnGaleria(Bitmap bitmap) {
        String nombreImagen = "foto_" + System.currentTimeMillis() + ".jpg"; // Nombre de archivo único
        String rutaImagen = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, nombreImagen, null);

        if (rutaImagen != null) {
            // Escanear el archivo para que sea detectado por la aplicación de la galería
            Intent intentScan = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uriImagen = Uri.parse(rutaImagen);
            intentScan.setData(uriImagen);
            sendBroadcast(intentScan);

            Toast.makeText(getApplicationContext(), "Foto guardada en la galería", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error al guardar la foto en la galería", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permiso_camara) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tomarfoto(); // Si se otorgan permisos, se llama a tomarfoto()
            } else {
                Toast.makeText(getApplicationContext(), "PERMISO DENEGADO", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == peticion_foto) { // Si la solicitud es para tomar una foto
                Bundle extras = data.getExtras();
                Bitmap imagen = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imagen); // Establece la imagen tomada en el ImageView

                // Guardar la foto en la galería
                guardarFotoEnGaleria(imagen);
            } else if (requestCode == seleccionar_foto) { // Si la solicitud es para seleccionar de la galería
                Uri path = data.getData();
                imageView.setImageURI(path); // Establece la imagen seleccionada de la galería en el ImageView
            }
        }
    }
}
