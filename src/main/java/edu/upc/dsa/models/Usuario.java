package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    String idUsuario;

    String nombre;

    String apellido;

    String apellido2;

    Double dsaCoins = 25.00;

    int vida = 50;

    private List<Producto> listaProductosComprados = null;

    private Juego equipo;

    private Partida partida;

    public Usuario() {

    }

    public Usuario(String idUsuario, String nombre, String apellido, String apellido2) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.listaProductosComprados = new ArrayList<>();
    }

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Double getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(Double dsaCoins) {
        this.dsaCoins = dsaCoins;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public List<Producto> getListaProductosComprados() {
        return listaProductosComprados;
    }

    public void setListaProductosComprados(List<Producto> listaProductosComprados) {
        this.listaProductosComprados = listaProductosComprados;
    }

    public Juego getEquipo() {
        return equipo;
    }

    public void setEquipo(Juego equipo) {
        this.equipo = equipo;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
