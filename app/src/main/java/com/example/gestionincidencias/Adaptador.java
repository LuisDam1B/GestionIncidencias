package com.example.gestionincidencias;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter {

    ArrayList<Reparacion> datos = new ArrayList<>();
    Context context;

    public Adaptador(ArrayList<Reparacion> datos, Context context)
    {
        this.context = context;
        this.datos = datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_reparaciones,parent,false);

        Holder holder = new Holder(view,context);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ((Holder)holder).binHolder(datos.get(position));

    }
    @Override
    public int getItemCount() {
        return datos.size();
    }
}

class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView tvCodigo;
    TextView tvNombre;
    TextView tvFecha;
    ImageView imageEstrella;
    LinearLayout contenedor;
    Reparacion reparacion;
    Context context;
    public Holder(@NonNull View itemView, Context context) {
        super(itemView);
        tvCodigo = itemView.findViewById(R.id.tvCodigo);
        tvNombre = itemView.findViewById(R.id.tvNombre);
        tvFecha = itemView.findViewById(R.id.tvFecha);
        imageEstrella = itemView.findViewById(R.id.imageEstrella);
        imageEstrella.setOnClickListener(this);
        contenedor = itemView.findViewById(R.id.contenedorItem);

        this.context = context;
    }

    public void binHolder(Reparacion reparacion)
    {
        this.reparacion = reparacion;
        tvCodigo.setText(reparacion.getCodigo());
        tvNombre.setText(reparacion.getTecnico());
        tvFecha.setText(reparacion.getFchEntrada());
        if (reparacion.getFchSolucion().isEmpty()){
            imageEstrella.setVisibility(View.INVISIBLE);

        }

    }

    @Override
    public void onClick(View view) {


    }
}