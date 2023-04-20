package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    }

    @Test
    public void addUsuario() {
        manager.addUsuario("3","Alberto","hola", "adios");
    }

    public void addProducto() {
        manager.addProducto("pocima+","cura 15 puntos de vida",7.99);
    }

    @Test
    public void Compra(){
        manager.hacerCompra("1", "pocima");
        manager.hacerCompra("2", "pocima");
        manager.hacerCompra("2", "charmander");
    }

    @After
    public void tearDown () { manager.clear();}
}
