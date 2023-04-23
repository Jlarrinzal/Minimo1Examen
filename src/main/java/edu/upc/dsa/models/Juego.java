package edu.upc.dsa.models;

import java.util.ArrayList;

public class Juego {
    private String nombre;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Juego> listaEquipos;
    private int numeroUsuarios;

    public Juego() {

    }

    public Juego(String nombre) {
        this.nombre = nombre;
        this.listaUsuarios = new ArrayList<>();
        this.listaEquipos = new ArrayList<>();
    }

    public void añadirJugador(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Juego> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Juego> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public int getNumeroUsuarios() {
        return numeroUsuarios;
    }

    public void setNumeroUsuarios(int numeroUsuarios) {
        this.numeroUsuarios = numeroUsuarios;
    }

    public boolean equipoLleno(){
        return listaUsuarios.size() == numeroUsuarios;
    }
}
