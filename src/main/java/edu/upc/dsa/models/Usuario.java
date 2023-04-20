package edu.upc.dsa.models;

import java.util.List;

public class Usuario {

    String idUsuario;

    String nombre;

    String apellido;

    String apellido2;

    Double dsaCoins = 25.00;

    public Usuario() {

    }

    List<Producto> listaProductosComprados = null;

    public Usuario(String idUsuario, String nombre, String apellido, String apellido2) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
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

    public List<Producto> getListaProductosComprados() {
        return listaProductosComprados;
    }

    public void setListaProductosComprados(List<Producto> listaProductosComprados) {
        this.listaProductosComprados = listaProductosComprados;
    }
}
