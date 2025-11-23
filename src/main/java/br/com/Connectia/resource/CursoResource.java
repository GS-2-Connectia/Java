package br.com.Connectia.resource;

import br.com.Connectia.bo.CursoBo;
import br.com.Connectia.model.Curso;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    @Inject
    CursoBo bo;

    // ================================
    // LISTAR TODOS
    // ================================
    @GET
    @Path("/cursos")
    public Response listar() {
        try {
            List<Curso> lista = bo.listar();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // ================================
    // BUSCAR POR ID
    // ================================
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Curso c = bo.buscarPorId(id);
            return Response.ok(c).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // ================================
    // CADASTRAR
    // ================================
    @POST
    public Response salvar(Curso c) {
        try {
            bo.salvar(c);
            return Response.status(Response.Status.CREATED).entity("Curso cadastrado com sucesso!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // ================================
    // ATUALIZAR
    // ================================
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Curso c) {
        try {
            c.setIdCurso(id);
            bo.atualizar(c);
            return Response.ok("Curso atualizado com sucesso!").build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
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
            return Response.ok("Curso excluído com sucesso!").build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
