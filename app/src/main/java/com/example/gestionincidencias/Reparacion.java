package com.example.gestionincidencias;

public class Reparacion {

    String codigo, tecnico, fchEntrada, fchSolucion, comentarios;

    public Reparacion() {
    }

    public Reparacion(String codigo, String tecnico, String fchEntrada, String fchSolucion, String comentarios ) {
        this.codigo = codigo;
        this.comentarios = comentarios;
        this.fchSolucion = fchSolucion;
        this.fchEntrada = fchEntrada;
        this.tecnico = tecnico;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFchEntrada(String fchEntrada) {
        this.fchEntrada = fchEntrada;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public void setFchSolucion(String fchSolucion) {
        this.fchSolucion = fchSolucion;
    }

    public String getTecnico() {
        return tecnico;
    }

    public String getFchEntrada() {
        return fchEntrada;
    }

    public String getFchSolucion() {
        return fchSolucion;
    }

    public String getComentarios() {
        return comentarios;
    }

}
