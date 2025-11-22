package Connectia.resource;

import Connectia.dao.UsuarioDao;
import Connectia.model.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

    @Path("/usuarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class UsuarioResource {

        @Inject
        UsuarioDao dao;

        @GET
        public Response listar() {
            return Response.ok(dao.listar()).build();
        }

        @GET
        @Path("/{id}")
        public Response buscarPorId(@PathParam("id") Long id) {
            Usuario usuario = dao.buscar(id);
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(usuario).build();
        }

        @POST
        public Response cadastrar(Usuario usuario) {
            dao.cadastrar(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        }

        @PUT
        @Path("/{id}")
        public Response atualizar(@PathParam("id") Long id, Usuario usuario) {
            usuario.setId(id);
            dao.atualizar(usuario);
            return Response.ok(usuario).build();
        }

        @DELETE
        @Path("/{id}")
        public Response remover(@PathParam("id") Long id) {
            dao.remover(id);
            return Response.noContent().build();
        }
    }

}
