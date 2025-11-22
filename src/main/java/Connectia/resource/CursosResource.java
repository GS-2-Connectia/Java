package Connectia.resource;


import Connectia.dao.CursosDao;
import Connectia.model.Cursos;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class CursosResource {

    @Path("/cursos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class CursosResource {

        @Inject
        CursosDao dao;

        @GET
        public Response listar() {
            return Response.ok(dao.listar()).build();
        }

        @GET
        @Path("/{id}")
        public Response buscarPorId(@PathParam("id") Long id) {
            Cursos curso = dao.buscar(id);
            if (curso == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(curso).build();
        }

        @POST
        public Response cadastrar(Cursos curso) {
            dao.cadastrar(curso);
            return Response.status(Response.Status.CREATED).entity(curso).build();
        }

        @PUT
        @Path("/{id}")
        public Response atualizar(@PathParam("id") Long id, Cursos curso) {
            curso.setId(id);
            dao.atualizar(curso);
            return Response.ok(curso).build();
        }

        @DELETE
        @Path("/{id}")
        public Response remover(@PathParam("id") Long id) {
            dao.remover(id);
            return Response.noContent().build();
        }
    }



}
