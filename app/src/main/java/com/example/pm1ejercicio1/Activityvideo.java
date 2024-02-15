package com.example.pm1ejercicio1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class Activityvideo extends AppCompatActivity {

    private static final int TOMA_VIDEO = 1; // Código de solicitud para la captura de video
    private static final int SELECCIONA_VIDEO = 2; // Código de solicitud para seleccionar un video de la galería
    private VideoView vv1; // Referencia al VideoView en el diseño de la actividad
    Button btnvervideo, btntomarvideo; // Declaración de botones para "ver video", "tomar video" y "guardar"
    Uri videoUri; // Variable para almacenar la URI del video capturado o seleccionado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityvideo); // Establecer el diseño de la actividad

        // Inicializar el VideoView
        vv1 = findViewById(R.id.videoView);

        btnvervideo = findViewById(R.id.btnvervideo); // Inicializar el botón "ver video"
        btntomarvideo = findViewById(R.id.btntomarvideo); // Inicializar el botón "tomar video"


        // Configurar OnClickListener para el botón de "ver video"
        btnvervideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si hay una URI de video disponible
                if (videoUri != null) {
                    // Asignar la URI del video al VideoView y empezar la reproducción
                    vv1.setVideoURI(videoUri);
                    vv1.start();
                }
            }
        });

        // Configurar OnClickListener para el botón de "tomar video"
        btntomarvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogo(); // Método para mostrar un diálogo de selección de opciones
            }
        });
    }

    private void mostrarDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar una opción")
                .setItems(new CharSequence[]{"Tomar video", "Seleccionar de la galería"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                tomarVideo(); // Método para iniciar la captura de video
                                break;
                            case 1:
                                seleccionarVideo(); // Método para seleccionar un video de la galería
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    private void tomarVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE); // Intent para capturar un video
        startActivityForResult(intent, TOMA_VIDEO); // Iniciar la actividad de captura de video
    }

    private void seleccionarVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI); // Intent para seleccionar un video de la galería
        intent.setType("video/*");
        startActivityForResult(intent, SELECCIONA_VIDEO); // Iniciar la actividad de selección de video
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TOMA_VIDEO:
                    videoUri = data.getData(); // Obtener la URI del video capturado
                    break;
                case SELECCIONA_VIDEO:
                    videoUri = data.getData(); // Obtener la URI del video seleccionado de la galería
                    break;
            }
        }
    }
}

