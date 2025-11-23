package br.com.Connectia.resource;

import br.com.Connectia.dao.CursoDao;
import br.com.Connectia.dto.cursos.*;
import br.com.Connectia.exception.CursoException;
import br.com.Connectia.model.Curso;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    @Inject
    CursoDao cursoDao;

    @Inject
    ModelMapper modelMapper;

    // ============================================================
    // LISTAR TODOS os cursos disponíveis na plataforma
    // ============================================================
    @GET
    public List<DetalhesCursoDto> listar() throws SQLException, CursoException {
        return cursoDao.listar()
                .stream()
                .map(c -> modelMapper.map(c, DetalhesCursoDto.class))
                .toList();
    }

    // ============================================================
    // BUSCAR curso por ID
    // ============================================================
    @GET
    @Path("/{idCurso}")
    public Response buscar(@PathParam("idCurso") int idCurso) throws SQLException, CursoException {
        Curso curso = cursoDao.buscar(idCurso);

        if (curso == null)
            throw new NotFoundException("Curso não encontrado");

        DetalhesCursoDto dto = modelMapper.map(curso, DetalhesCursoDto.class);
        return Response.ok(dto).build();
    }

    // ============================================================
    // LISTAR cursos do usuário (inscrições)
    // ============================================================
    @GET
    @Path("/usuario/{idUsuario}")
    public List<CursosDoUsuarioDto> listarCursosUsuario(@PathParam("idUsuario") int idUsuario) throws SQLException, CursoException {
        return cursoDao.listarCursosDoUsuario(idUsuario)
                .stream()
                .map(c -> modelMapper.map(c, CursosDoUsuarioDto.class))
                .toList();
    }

    // ============================================================
    // INSCREVER usuário em um curso
    // ============================================================
    @POST
    @Path("/{idCurso}/inscricao")
    public Response inscrever(@PathParam("idCurso") int idCurso,
                              @Valid InscricaoCursoDto dto,
                              @Context UriInfo uriInfo)
            throws SQLException, CursoException {

        cursoDao.inscrever(idCurso, dto.getIdUsuario(), dto.getIdArea());

        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response.created(uri).build();
    }

    // ============================================================
    // ATUALIZAR status do curso (N/E/C)
    // ============================================================
    @PUT
    @Path("/{idCurso}/status")
    public Response atualizarStatus(@PathParam("idCurso") int idCurso,
                                    @Valid AtualizarStatusCursoDto dto)
            throws SQLException, CursoException {

        cursoDao.atualizarStatus(idCurso, dto.getIdArea(), dto.getNovoStatus());
        return Response.ok().build();
    }

    // ============================================================
    // DESINSCREVER usuário de um curso
    // ============================================================
    @DELETE
    @Path("/{idCurso}/inscricao")
    public Response desinscrever(@PathParam("idCurso") int idCurso,
                                 @Valid DesinscricaoCursoDto dto)
            throws SQLException, CursoException {

        cursoDao.desinscrever(idCurso, dto.getIdArea());
        return Response.noContent().build();
    }

}
