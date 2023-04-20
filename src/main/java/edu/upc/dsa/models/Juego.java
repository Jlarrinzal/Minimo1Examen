package edu.upc.dsa.models;

public class Juego {

    String idJuego;

    Integer equipos;

    Integer personas;

    public Juego() {}

    public Juego(String idJuego, Integer equipos, Integer personas) {
        this.idJuego = idJuego;
        this.equipos = equipos;
        this.personas = personas;
    }

    public String getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public Integer getEquipos() {
        return equipos;
    }

    public void setEquipos(Integer equipos) {
        this.equipos = equipos;
    }

    public Integer getPersonas() {
        return personas;
    }

    public void setPersonas(Integer personas) {
        this.personas = personas;
    }
}
