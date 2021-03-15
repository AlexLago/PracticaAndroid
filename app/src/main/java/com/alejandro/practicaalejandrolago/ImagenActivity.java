package com.alejandro.practicaalejandrolago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

public class ImagenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
    }

    //cambioImagen -- Cambia la imagen de la portada
    //Se ejecutará al presionar el boton de cambiar imagen
    public void onClickcambiarImagen(View view){
        //Creo un spinner y lo relaciono al Spinner de pantalla
        Spinner imagen = (Spinner) findViewById(R.id.spinnerImagenes);

        //Creo un objeto imageView y lo relaciono al Spinner en la pantalla
        ImageView imagePort = (ImageView) findViewById(R.id.imagenPortada);

        //Leo lo que está seleccionado en el spinner
        String img = String.valueOf(imagen.getSelectedItem());

        //Cambio la imagen de la portada
        if(img.equals("arknights")){
            imagePort.setImageResource(R.drawable.arknights);
        }else if (img.equals("lol")){
            imagePort.setImageResource(R.drawable.lol);
        }else if (img.equals("closers")){
            imagePort.setImageResource(R.drawable.closers);
        }else{
            imagePort.setImageResource(R.drawable.genshin);
        }
    }
}