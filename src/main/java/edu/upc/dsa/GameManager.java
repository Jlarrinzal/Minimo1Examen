package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

public interface GameManager {
    //Crear juego
    public void crearJuego(Integer equipos, Integer personas);
    //Añadir Usuario
    public void addUsuario(String idUsuario, String nombre, String apellido, String apellido2);
    //Añadir Producto
    public void addProducto(String idProducto, String descripcion, Double precio);
    // Comprar un producto por parte de un usuario
    public Producto hacerCompra(String idUsuario, String idProducto);

    //inicio de la Partida por parte de un usuario
    public void startPartida(String idUsuario);
    //Consulta el estado del juego
    public String consultarEstadoJuego();
    //Actualización del valor de vida de un usuario
    public void actualizarVida(String idUsuario, int vida);
    //Consulta el valor de vida actual de un usuario
    public Integer consultarVida(String Usuario);
    //Consulta el valor de vida de un equipo
    public Integer consultarVidaEquipo(String idEquipo);
    //Finalizar el juego
    public void finalizarJuego();

    //extras

    Usuario getUsuarioPorNombre(String idUsuario);
    Producto getProductoPoridProducto(String idProducto);

    public void clear();

}
