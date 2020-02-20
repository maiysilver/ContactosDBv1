package com.example.contactosdb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class pantallaDetalles  extends AppCompatActivity {

    private static final String TAG_ACTIVITY =
            pantallaDetalles.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_detalle);
    }
}
