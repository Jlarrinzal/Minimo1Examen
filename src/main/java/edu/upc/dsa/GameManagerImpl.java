package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.upc.dsa.models.Exceptions.JuegoNoExisteException;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

public class GameManagerImpl implements GameManager {

    protected List<Usuario> listaUsuarios;
    protected List<Producto> listaProductos;

    protected List<Juego> listaJuegos;

    private static GameManagerImpl manager;

    public GameManagerImpl(){
        this.listaUsuarios = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaJuegos = new ArrayList<>();
    }

    public static GameManagerImpl getInstance(){
        if (manager == null){
            manager = new GameManagerImpl();
        }
        return manager;
    }

    static final java.util.logging.Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    @Override
    public Juego CrearJuego(String idJuego, Integer equipos, Integer personas) {
        Juego j = getJuego(idJuego);
        if (j==null){
            Juego ju = new Juego(idJuego, equipos, personas);
            listaJuegos.add(ju);
            logger.info("Se ha creado el juego: " + ju);
            return ju;
        }
        else{
            logger.info("El juego ya está creado");
        }
        return null;
    }

    @Override
    public void addUsuario(String idUsuario, String nombre, String apellido, String apellido2) {

        this.listaUsuarios.add(new Usuario(idUsuario, nombre, apellido, apellido2));

        logger.info("El usuario se ha registrado correctamente");
    }

    @Override
    public void addProducto(String idProducto, String descripcion, Double precio) {

        this.listaProductos.add(new Producto(idProducto, descripcion, precio));

        logger.info("El producto se ha añadido correctamente");
    }

    @Override
    public Producto hacerCompra(String idUsuario, String idProducto) {

        Usuario usuario = getUsuarioPorNombre(idUsuario);
        if (usuario == null) {
            logger.info("El usuario " + idUsuario + "no existe en la base de datos");
        }
        else {
            Producto producto = getProductoPoridProducto(idProducto);
            if (usuario.getDsaCoins() < producto.getPrecio()) {
                logger.info("No tienes money");
            }
            else{
                usuario.getListaProductosComprados().add(producto);
                double dinero = usuario.getDsaCoins() - producto.getPrecio();
                usuario.setDsaCoins(dinero);
                logger.info("Objeto" + idProducto + "comprado");
                logger.info(idUsuario + "ahora tienes:" + producto + "dsaCoins");
                return producto;
            }
        }
        return null;

    }

    @Override
    public Partida startPartida(String idUsuario) {
        return null;
    }

    @Override
    public Juego consultaEstadoJuego() {
        return null;
    }

    @Override
    public Usuario actualizarVida(String idUsuario) {
        return null;
    }

    @Override
    public Usuario consultarVida(String idUsuario) {
        return null;
    }

    @Override
    public Partida consultarVidaEquipo() {
        return null;
    }

    @Override
    public void finalizarJuego() {

    }


    //extra

    @Override
    public int size() {
        return listaJuegos.size();
    }
    public Usuario getUsuarioPorNombre(String nombre){
        for (Usuario u: this.listaUsuarios) {
            if(u.getNombre().equals(nombre)){
                return u;
            }
        }
        return null;
    }

    public Producto getProductoPoridProducto(String idProducto){
        for (Producto p: this.listaProductos) {
            if(p.getIdProducto().equals(idProducto)){
                return p;
            }
        }
        return null;
    }


    public Juego getJuego(String idJuego) {
        for(Juego juego : this.listaJuegos) {
            if(juego.getIdJuego().equals(idJuego)){
                return juego;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        this.listaUsuarios.clear();
        this.listaProductos.clear();
    }

}