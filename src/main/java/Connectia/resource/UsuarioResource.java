package Connectia.resource;

import Connectia.bo.UsuarioBo;
import Connectia.model.Usuario;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioBo bo;


    @GET
    public Response listar() throws Exception {
        List<Usuario> lista = bo.listar();
        return Response.ok(lista).build();
    }


    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Usuario usuario = bo.buscarPorId(id);
            return Response.ok(usuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ================================
    // CADASTRAR
    // ================================
    @POST
    public Response salvar(Usuario usuario) {
        try {
            bo.salvar(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity("Usuário cadastrado com sucesso!")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ================================
    // ATUALIZAR
    // ================================
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Usuario usuario) {
        try {
            usuario.setIdUsuario(id);  // <-- este é o nome correto
            bo.atualizar(usuario);
            return Response.ok("Usuário atualizado com sucesso!").build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // ================================
    // EXCLUIR
    // ================================
    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        try {
            bo.excluir(id);
            return Response.ok("Usuário excluído com sucesso!").build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
