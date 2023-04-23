package edu.upc.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

public class GameManagerImpl implements GameManager {

    private Map<Integer, Usuario> usuarios;
    private List<Usuario> listaUsuarios;
    private Map<Integer, Producto> productos;
    private List<Producto> listaProductos;
    private Map<Integer, Juego> equipos;
    private List<Juego> listaEquipos;
    private int estado;
    private static final int NO_INICIADO = 0;
    private static final int INICIADO_EN_PREPARACION = 1;
    private static final int INICIADO_EN_FUNCIONAMIENTO = 2;
    private static final int FINALIZADO = 3;

    static final Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    private static GameManagerImpl manager;

    public static GameManagerImpl getInstance(){
        if (manager == null){
            manager = new GameManagerImpl();
        }
        return manager;
    }

    public GameManagerImpl(){
        this.usuarios = new HashMap<>();
        this.productos = new HashMap<>();
        this.equipos = new HashMap<>();
        this.listaEquipos = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaProductos= new ArrayList<>();
        estado=NO_INICIADO;
    }

    @Override
    public void crearJuego(Integer equipos, Integer personas) {
        if (estado != NO_INICIADO) {
            logger.info("El juego está iniciado");
        }

        for(int i = 1; i < equipos; i++){
            Juego equipo = new Juego( "" + i);
            for (int j = 1; j < personas; j++){
                Usuario u = new Usuario("Personas: " + j);
                equipo.añadirJugador(u);
            }
            listaEquipos.add(equipo);

        }
        estado = INICIADO_EN_PREPARACION;
        logger.info("El juego ha sido creado");

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
        Usuario usuario = getUsuarioPoridUsuario(idUsuario);
        if (usuario == null) {
            logger.info("El usuario " + idUsuario + " no existe en la base de datos");
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
                logger.info("Objeto " + idProducto + " comprado");
                logger.info(idUsuario + "ahora tienes: " + producto + " dsaCoins");
                return producto;
            }
        }
        return null;
    }

    @Override
    public void startPartida(String idUsuario) {

        if (estado == NO_INICIADO) {
            logger.info("El juego no ha sido creado todavía");
        }

        Usuario usuario = getUsuarioPoridUsuario(idUsuario);
        if (usuario == null) {
            logger.info("El usuario " + idUsuario + " no existe");
        }

        if (usuario.getPartida() != null) {
            logger.info(idUsuario + " Tienes una partida activa");
        }

        Juego equipo = obtenerCapacidadEquipo();
        equipo.añadirJugador(usuario);
        usuario.setEquipo(equipo);
        usuario.setPartida(new Partida(equipo));
        logger.info(idUsuario + " Te ha tocado equipo: " + equipo.getNombre());
        estado = INICIADO_EN_FUNCIONAMIENTO;
        logger.info("Ha comenzado la partida");
    }

    @Override
    public String consultarEstadoJuego() {
        if (estado == 0) {
            logger.info("El juego no está iniciado");
        } else if (estado == INICIADO_EN_PREPARACION) {
            logger.info("El juego esta INICIADO_EN_PREPARACION");
        } else if (estado == INICIADO_EN_FUNCIONAMIENTO) {
            logger.info("El juego esta INICIADO_EN_FUNCIONAMIENTO");
        } else if (estado == FINALIZADO) {
            logger.info("El juego esta FINALIZADO");
        }
        return "Sorpresa, no existe";
    }


    @Override
    public void actualizarVida(String idUsuario, int vida) {
        if (estado != INICIADO_EN_FUNCIONAMIENTO) {
            logger.info("No existe la partida");
        }
        Usuario usuario = getUsuarioPoridUsuario(idUsuario);
        if (usuario == null) {
            logger.info("El usuario no existe");
        }
        int vidaActualiada = usuario.getVida() - vida;
        if (vidaActualiada <= 0) {
            vidaActualiada = 0;
            logger.info(idUsuario + " no te queda vida (RIP)");
        }
        usuario.setVida(vidaActualiada);
        logger.info(idUsuario + " tienes " + vidaActualiada);
    }

    @Override
    public Integer consultarVida(String idUsuario) {
        Usuario usuario = getUsuarioPoridUsuario(idUsuario);
        if (usuario == null) {
            logger.info("El usuario no existe");
        }
        if (usuario.getPartida() == null) {
            logger.info("No está en partida");
        }
        logger.info("la vida es" + usuario.getVida());
        return usuario.getVida();
    }

    @Override
    public Integer consultarVidaEquipo(String idEquipo) {
        int vidaTotalEquipo = 0;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEquipo().getNombre().equals(idEquipo)) {
                vidaTotalEquipo += usuario.getVida();
            }
        }
        return vidaTotalEquipo;
    }

    @Override
    public void finalizarJuego() {
        if (estado != INICIADO_EN_FUNCIONAMIENTO) {
            logger.info("Aun hay una partida en funcionamiento");
            return;
        }
        estado = FINALIZADO;
        logger.info("La partida ha acabado");
    }

    private Juego obtenerCapacidadEquipo() {
        for (Juego equipo : listaEquipos) {
            if (!equipo.equipoLleno()) {
                return equipo;
            }
        }
        return null;
    }

    public Usuario getUsuarioPoridUsuario(String idUsuario){
        for (Usuario u: this.listaUsuarios) {
            if(u.getIdUsuario().equals(idUsuario)){
                return u;
            }
        }
        return null;
    }
    @Override
    public Producto getProductoPoridProducto(String idProducto){
        for (Producto p: this.listaProductos) {
            if(p.getIdProducto().equals(idProducto)){
                return p;
            }
        }
        return null;
    }

    @Override
    public Usuario getUsuarioPorNombre(String idUsuario) {
        return null;
    }


    @Override
    public void clear() {
        this.listaUsuarios.clear();
        this.listaProductos.clear();
    }

}