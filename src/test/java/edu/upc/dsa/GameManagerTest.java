package edu.upc.dsa;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameManagerTest {

    GameManager manager = GameManagerImpl.getInstance();

    Logger logger = Logger.getLogger(GameManagerTest.class);
    @Before
    public void Inicializar() {

        manager = new GameManagerImpl();
        manager.addUsuario("1", "Jose", "Larrinzal", "090700");
        manager.addUsuario("2", "A", "Ji", "090700");

        manager.addProducto("pocima","otorga 10 puntos de vida", 5.99);
        manager.addProducto("charmander","pokemon", 45.99);

        manager.crearJuego(2,3);

    }

    @Test
    public void addUsuario() {
        manager.addUsuario("3","Alberto","hola", "adios");
    }

    @Test
    public void addProducto() {
        manager.addProducto("pocima+","cura 15 puntos de vida",7.99);
    }

    @Test
    public void Compra(){
        manager.hacerCompra("1", "pocima");
        manager.hacerCompra("2", "pocima");
        manager.hacerCompra("2", "charmander");
    }

    @Test
    public void consultarEstadoJuego() {
        manager.consultarEstadoJuego();
    }

    @Test
    public void actualizarVida() {
        manager.actualizarVida("1",10);
    }

    @Test
    public void crearJuego() {
        manager.crearJuego(2,2);

    }

    @Test
    public void startPartida() {
        manager.crearJuego(3,4);
        manager.startPartida("1");
        manager.startPartida("2");

    }

    @Test
    public void consultarVida() {
        manager.actualizarVida("1",10);
        manager.consultarVida("1");
    }

    @Test
    public void ConsultarVidaEquipo() {
        manager.startPartida("1");
        manager.consultarVidaEquipo("1");
    }

    @Test
    public void finalizarPartida() {
        manager.finalizarJuego();
    }


    @After
    public void tearDown () { manager.clear();}
}
