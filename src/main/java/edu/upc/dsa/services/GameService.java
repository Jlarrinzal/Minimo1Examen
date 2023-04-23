package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/Game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager manager;

    public GameService() {
        this.manager = GameManagerImpl.getInstance();

    }

    //crear Juego
    @POST
    @ApiOperation(value = "crear Juego", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/creategame")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJuego(Juego game) {

        if (game == null) return Response.status(500).entity(game).build();
        this.manager.crearJuego(1, 2);
        return Response.status(201).entity(game).build();
    }

    //start partida
    @PUT
    @ApiOperation(value = "start Partida", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully started", response= Partida.class),
            @ApiResponse(code = 404, message = "No existe"),

    })
    @Path("/startpartida")
    public Response startPartida(Usuario usuario) {

            this.manager.startPartida(usuario.getIdUsuario());
            return Response.status(201).entity(usuario).build();

    }
    //Consulta estado de la partida
    @GET
    @ApiOperation(value = "consultar estado de la partida", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 404, message = "La partida no existe"),
    })
    @Path("/{idUsuario}/estadoPartida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNivelActual(@PathParam("idUsuario") String idUsuario) {

            this.manager.consultarEstadoJuego();
            return Response.status(200).entity(idUsuario).build();
    }

    //actualizar Vida
    @POST
    @ApiOperation(value = "Actualizar vida", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/actualizarVida")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarVida(Usuario usuario) {

        if (usuario.getIdUsuario() == null) return Response.status(500).entity(usuario).build();
        this.manager.actualizarVida(usuario.getIdUsuario(), usuario.getVida());
        return Response.status(201).entity(usuario).build();
    }

    //Consultar vida
    @GET
    @ApiOperation(value = "consultar vida del usuario", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 404, message = "El usuario no existe"),
    })
    @Path("/{idUsuario}/consultarVida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarVida(@PathParam("idUsuario") String idUsuario) {

        this.manager.consultarVida(idUsuario);
        return Response.status(200).entity(idUsuario).build();
    }

    //Consultar vida equipo
    @GET
    @ApiOperation(value = "consultar vida del equipo", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 404, message = "El equipo no existe"),
    })
    @Path("/{idEquipo}/consultarVidaEquipo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarVidaEquipo(@PathParam("idEquipo") String idUsuario) {

        this.manager.consultarVidaEquipo(idUsuario);
        return Response.status(200).entity(idUsuario).build();
    }

    //Añadimos usuario
    @POST
    @ApiOperation(value = "Añadir usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/AñadirUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario usuario) {

        if (usuario.getNombre() == null) return Response.status(500).entity(usuario).build();
        this.manager.addUsuario(usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getApellido2());
        return Response.status(201).entity(usuario).build();
    }

    //Añadir producto
    @POST
    @ApiOperation(value = "crear producto nuevo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/añadirProducto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProducto(Producto producto) {

        if (producto.getIdProducto() == null || producto.getDescripcion() == null || producto.getPrecio() == 0.00)
            return Response.status(500).entity(producto).build();
        this.manager.addProducto(producto.getIdProducto(), producto.getDescripcion(), producto.getPrecio());
        return Response.status(201).entity(producto).build();
    }

    // comprar objetos por parte de un usuario
    @POST
    @ApiOperation(value = "comprar Productos", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/compraProductos/{idUsuario}/{idProducto}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response compraObjeto(@PathParam("idUsuario") String idUsuario, @PathParam("idProducto") String idProducto) {
        Producto producto = this.manager.getProductoPoridProducto(idProducto);
        Usuario usuario = this.manager.getUsuarioPorNombre(idUsuario);
        if (producto.getIdProducto() == null || producto.getDescripcion() == null) return Response.status(500).build();
        this.manager.hacerCompra(usuario.getIdUsuario(), producto.getIdProducto());
        return Response.status(201).entity(producto).build();
    }

    @POST
    @ApiOperation(value = "finaliza la partida", notes = "a")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/finalizaPartida")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response finalizaPartida(Usuario usuario) {

        if (usuario.getIdUsuario() == null) return Response.status(500).entity(usuario).build();
        this.manager.finalizarJuego();
        return Response.status(201).entity(usuario).build();
    }
}