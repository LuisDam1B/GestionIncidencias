package com.example.gestionincidencias;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Actividad2RecyclerView extends AppCompatActivity {

    ArrayList<Reparacion> datos = new ArrayList<>();

    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentrecycleview);
        recyclerView = findViewById(R.id.recycleview);
        añadirDatos();
        Adaptador adaptador = new Adaptador(datos,this);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));

    }


    void añadirDatos()
    {
        datos.add(new Reparacion("1","Ana Sogor","01/11/2019","05/11/2019","El disco duro roto, cambiado por uno nuevo"));
        datos.add(new Reparacion("2","Ana Sogor","01/11/2019","02/11/2019","Se ha instalado el sistema operativo de nuevo"));
        datos.add(new Reparacion("3","Sofia Nieto","03/11/2019","05/11/2019","Problemas con la RAM, se ha sustituido"));
        datos.add(new Reparacion("4","Luis García","04/11/2019","","Problemas con la placa base, a espera de que llegue la nueva"));
        datos.add(new Reparacion("5","Luis García","04/11/2014","05/11/2019","Se ha liberado espacio en disco, y se ha añadido RAM"));
    }
}
