package com.example.gestionincidencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int ACTIVIDAD2RecycleWiew = 0;

    //hinchar controles necesarios

    Button btListarResparaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btListarResparaciones = findViewById(R.id.btListar);

        btListarResparaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Actividad2RecyclerView.class);
                startActivity(intent);



            }
        });
    }


}
