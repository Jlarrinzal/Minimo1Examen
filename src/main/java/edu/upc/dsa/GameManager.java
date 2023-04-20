package edu.upc.dsa;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

public interface GameManager {

    //Crear Juego
    public Juego CrearJuego(String idJuego, Integer equipos, Integer personas);
    //Añadir Usuario
    public void addUsuario(String idUsuario, String nombre, String apellido, String apellido2);
    //Añadir Producto
    public void addProducto(String idProducto, String descripcion, Double precio);
    // Comprar un producto por parte de un usuario
    public Producto hacerCompra(String idUsuario, String idProducto);

    //inicio de la Partida por parte de un usuario
    public Partida startPartida(String idUsuario);
    //Consulta el estado del juego
    public Juego consultaEstadoJuego();
    //Actualización del valor de vida de un usuario
    public Usuario actualizarVida(String idUsuario);
    //Consulta el valor de vida actual de un usuario
    public Usuario consultarVida(String idUsuario);
    //Consulta el valor de vida de un equipo
    public Partida consultarVidaEquipo();
    //Finalizar el juego
    public void finalizarJuego();
    //extras
    public int size();
    Usuario getUsuarioPorNombre(String idUsuario);

    Producto getProductoPoridProducto(String idProducto);

    public void clear();
}
