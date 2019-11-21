package com.example.gestionincidencias;

import android.os.Bundle;
import android.util.Xml;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Actividad2RecyclerView extends AppCompatActivity {

   // ArrayList<Reparacion> datos = new ArrayList<>();
    ArrayList<Reparacion> datosLeidos = new ArrayList<>();

    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentrecycleview);
        recyclerView = findViewById(R.id.recycleview);

        leerXML();
        Adaptador adaptador = new Adaptador(datosLeidos,this);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));

    }


    void añadirDatos() {
        datosLeidos.add(new Reparacion("1","Ana Sogor","01/11/2019","05/11/2019","El disco duro roto, cambiado por uno nuevo"));
        datosLeidos.add(new Reparacion("2","Ana Sogor","01/11/2019","02/11/2019","Se ha instalado el sistema operativo de nuevo"));
        datosLeidos.add(new Reparacion("3","Sofia Nieto","03/11/2019","05/11/2019","Problemas con la RAM, se ha sustituido"));
        datosLeidos.add(new Reparacion("4","Luis García","04/11/2019","","Problemas con la placa base, a espera de que llegue la nueva"));
        datosLeidos.add(new Reparacion("5","Luis García","04/11/2014","05/11/2019","Se ha liberado espacio en disco, y se ha añadido RAM"));
    }
    private void leerXML() {
        
        XmlPullParser parser = Xml.newPullParser();
        FileInputStream fileInputStream = null;


        //abrimos el fichero
        try {
            fileInputStream = openFileInput("datos.xml");

            parser.setInput(fileInputStream, null);

            // variable que usamos para recorrer el documento.
            int evento = parser.getEventType();

            Reparacion reparacion = null;
            datosLeidos = null;

            while (evento != XmlPullParser.END_DOCUMENT) {
                switch (evento) {
                    case XmlPullParser.START_DOCUMENT:
                        datosLeidos = new ArrayList<>();
                        break;

                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("Reparacion")) {
                            reparacion = new Reparacion();
                            reparacion.setFchEntrada(parser.getAttributeValue(null,"FechaEntrada"));

                        } else if (parser.getName().equals("Codigo")) {
                            reparacion.setCodigo(parser.nextText());
                        } else if (parser.getName().equals("Tecnico")) {
                            reparacion.setFchSolucion(parser.getAttributeValue(null,"FechaSalida"));
                            reparacion.setTecnico(parser.nextText());

                        } else if (parser.getName().equals("Comentarios")) {
                            reparacion.setComentarios(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("Reparacion")) {
                            datosLeidos.add(reparacion);
                        }
                        break;

                }
                // avanzamos en la lectura
                evento = parser.next();

            }

            fileInputStream.close();

            Toast.makeText(Actividad2RecyclerView.this, "Lectura Correcta", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            añadirDatos();


            Toast.makeText(Actividad2RecyclerView.this, e.getMessage() + "Fichero no encontrado", Toast.LENGTH_LONG).show();

        } catch (XmlPullParserException e) {
            Toast.makeText(Actividad2RecyclerView.this, e.getMessage() + "Fallo lectura", Toast.LENGTH_LONG).show();

            e.printStackTrace();

        } catch (IOException e) {
            Toast.makeText(Actividad2RecyclerView.this, e.getMessage() + "Ffallo fichero", Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
    }
    private void escribirXML() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("datos.xml", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            Toast.makeText(Actividad2RecyclerView.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        XmlSerializer serializer = Xml.newSerializer();

        try {
            serializer.setOutput(fileOutputStream, "UTF-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            // Empezamos a definir nuestro XML
            //ETIQUETA INICIO
            serializer.startTag(null, "Reparaciones");

            for (Reparacion reparacion: datosLeidos) {
                serializer.startTag(null, "Reparacion");
                serializer.attribute(null,"FechaEntrada",reparacion.getFchEntrada());

                serializer.startTag(null, "Codigo");
                serializer.text(reparacion.getCodigo());
                serializer.endTag(null, "Codigo");

                serializer.startTag(null, "Tecnico");
                serializer.attribute(null,"FechaSalida",reparacion.getFchSolucion());
                serializer.text(reparacion.getTecnico());
                serializer.endTag(null, "Tecnico");

                serializer.startTag(null, "Comentarios");
                serializer.text(reparacion.getComentarios());
                serializer.endTag(null, "Comentarios");

                serializer.endTag(null, "Reparacion");
            }

            //CIERRE DE ETIQUETA PRINCIPAL
            serializer.endTag(null, "Reparaciones");
            serializer.endDocument();
            serializer.flush();
            fileOutputStream.close();


        } catch (Exception e) {
            Toast.makeText(Actividad2RecyclerView.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
