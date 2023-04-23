package edu.upc.dsa.models;

public class Partida {

    private Juego equipo;
    private boolean partidaFinalizada;

    public Partida() {

    }
    public Partida(Juego equipo) {
        this.equipo = equipo;
        this.partidaFinalizada = false;
    }

    public Juego getEquipo() {
        return equipo;
    }

    public void setEquipo(Juego equipo) {
        this.equipo = equipo;
    }

    public boolean isPartidaFinalizada() {
        return partidaFinalizada;
    }

    public void setPartidaFinalizada(boolean partidaFinalizada) {
        this.partidaFinalizada = partidaFinalizada;
    }

    public void finalizar() {
        this.partidaFinalizada = true;
    }
}
