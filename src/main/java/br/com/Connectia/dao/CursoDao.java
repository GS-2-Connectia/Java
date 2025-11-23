package br.com.Connectia.dao;

import br.com.Connectia.model.Curso;
import br.com.Connectia.exception.CursoException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CursoDao {

    @Inject
    DataSource dataSource;

    // ============================================================
    // LISTAR TODOS OS CURSOS DISPONÍVEIS
    // ============================================================
    public List<Curso> listar() throws CursoException {
        List<Curso> cursos = new ArrayList<>();

        String sql = """
                SELECT ID_CURSO, NM_CURSO, DS_CURSO, TP_CONTEUDO,
                       DT_INICIO, STS_CURSO, ID_USUARIO, ID_AREA
                FROM T_CON_CURSOS
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) cursos.add(parseCurso(rs));

        } catch (SQLException e) {
            throw new CursoException("Erro ao listar cursos: " + e.getMessage());
        }

        return cursos;
    }

    // ============================================================
    // BUSCAR CURSO POR ID  (Resource espera "buscar")
    // ============================================================
    public Curso buscar(int idCurso) throws CursoException {
        String sql = """
                SELECT ID_CURSO, NM_CURSO, DS_CURSO, TP_CONTEUDO,
                       DT_INICIO, STS_CURSO, ID_USUARIO, ID_AREA
                FROM T_CON_CURSOS
                WHERE ID_CURSO = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCurso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return parseCurso(rs);
                return null;
            }

        } catch (SQLException e) {
            throw new CursoException("Erro ao buscar curso: " + e.getMessage());
        }
    }

    // ============================================================
    // LISTAR CURSOS DO USUÁRIO (Resource espera "listarCursosDoUsuario")
    // ============================================================
    public List<Curso> listarCursosDoUsuario(int idUsuario) throws CursoException {
        List<Curso> cursos = new ArrayList<>();

        String sql = """
                SELECT ID_CURSO, NM_CURSO, DS_CURSO, TP_CONTEUDO,
                       DT_INICIO, STS_CURSO, ID_USUARIO, ID_AREA
                FROM T_CON_CURSOS
                WHERE ID_USUARIO = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) cursos.add(parseCurso(rs));
            }

        } catch (SQLException e) {
            throw new CursoException("Erro ao listar cursos do usuário: " + e.getMessage());
        }

        return cursos;
    }

    // ============================================================
    // INSCREVER USUÁRIO (Resource espera "inscrever")
    // ============================================================
    public void inscrever(int idCurso, int idUsuario, int idArea) throws CursoException {
        String sql = """
                UPDATE T_CON_CURSOS
                SET ID_USUARIO = ?,
                    STS_CURSO = 'N',
                    DT_INICIO = SYSDATE
                WHERE ID_CURSO = ?
                AND ID_AREA = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idCurso);
            ps.setInt(3, idArea);

            if (ps.executeUpdate() == 0) {
                throw new CursoException("Falha ao inscrever: curso não encontrado.");
            }

        } catch (SQLException e) {
            throw new CursoException("Erro ao inscrever usuário no curso: " + e.getMessage());
        } catch (CursoException e) {
            throw new RuntimeException(e);
        }
    }

    // ============================================================
    // ATUALIZAR STATUS (N/E/C)
    // ============================================================
    public void atualizarStatus(int idCurso, int idArea, String novoStatus) throws CursoException {
        String sql = """
                UPDATE T_CON_CURSOS
                SET STS_CURSO = ?
                WHERE ID_CURSO = ?
                AND ID_AREA = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, novoStatus);
            ps.setInt(2, idCurso);
            ps.setInt(3, idArea);

            if (ps.executeUpdate() == 0) {
                throw new CursoException("Falha ao atualizar status: curso não encontrado.");
            }

        } catch (SQLException e) {
            throw new CursoException("Erro ao atualizar status: " + e.getMessage());
        } catch (CursoException e) {
            throw new RuntimeException(e);
        }
    }

    // ============================================================
    // DESINSCREVER USUÁRIO (Resource espera "desinscrever")
    // ============================================================
    public void desinscrever(int idCurso, int idArea) throws CursoException {
        String sql = """
                UPDATE T_CON_CURSOS
                SET ID_USUARIO = NULL,
                    STS_CURSO = 'N',
                    DT_INICIO = NULL
                WHERE ID_CURSO = ?
                AND ID_AREA = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCurso);
            ps.setInt(2, idArea);

            if (ps.executeUpdate() == 0) {
                throw new CursoException("Falha ao desinscrever: curso não encontrado.");
            }

        } catch (SQLException | CursoException e) {
            throw new CursoException("Erro ao desinscrever usuário: " + e.getMessage());
        }
    }

    // ============================================================
    // MAPEAR RESULTSET → OBJETO
    // ============================================================
    private Curso parseCurso(ResultSet rs) throws SQLException {
        Curso c = new Curso();
        c.setIdCurso(rs.getInt("ID_CURSO"));
        c.setNomeCurso(rs.getString("NM_CURSO"));
        c.setDescricaoCurso(rs.getString("DS_CURSO"));
        c.setTipoConteudo(rs.getString("TP_CONTEUDO"));
        c.setDataInicio(rs.getDate("DT_INICIO"));
        c.setStatusCurso(rs.getString("STS_CURSO"));
        c.setIdUsuario(rs.getInt("ID_USUARIO"));
        c.setIdArea(rs.getInt("ID_AREA"));
        return c;
    }
}
