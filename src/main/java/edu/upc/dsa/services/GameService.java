package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager manager;

    public GameService() {
        this.manager = GameManagerImpl.getInstance();
        //if (manager.size()==0) {
        //  this.manager.addTrack("La Barbacoa", "Georgie Dann");
        //this.manager.addTrack("Despacito", "Luis Fonsi");
        //this.manager.addTrack("Enter Sandman", "Metallica");
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
    @ApiOperation(value = "crear objeto nuevo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/addObjeto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjeto(Producto producto) {

        if (producto.getIdProducto() == null || producto.getDescripcion() == null || producto.getPrecio() == 0.00)
            return Response.status(500).entity(producto).build();
        this.manager.addProducto(producto.getIdProducto(), producto.getDescripcion(), producto.getPrecio());
        return Response.status(201).entity(producto).build();
    }

    // comprar objetos por parte de un usuario
    @POST
    @ApiOperation(value = "crear objeto nuevo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/compraObjeto/{idUsuario}/{nombreObjeto}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response compraObjeto(@PathParam("idUsuario") String idUsuario, @PathParam("nombreObjeto") String idProducto) {
        Producto producto = this.manager.getProductoPoridProducto(idProducto);
        Usuario usuario = this.manager.getUsuarioPorNombre(idUsuario);
        if (producto.getIdProducto() == null || producto.getDescripcion() == null) return Response.status(500).build();
        this.manager.hacerCompra(usuario.getIdUsuario(), producto.getIdProducto());
        return Response.status(201).entity(producto).build();
    }
}