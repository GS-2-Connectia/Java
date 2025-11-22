package Connectia.dao;

import Connectia.model.Cursos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CursosDao {

    @Inject
    DataSource dataSource;

    // ================================
    // LISTAR
    // ================================
    public List<Cursos> listar() throws Exception {
        List<Cursos> lista = new ArrayList<>();

        String sql = """
                SELECT id_curso, id_carreira, id_area, nome, descricao, tipo_conteudo, data_inicio, status
                FROM cursos
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(criarObjeto(rs));
            }
        }

        return lista;
    }

    // ================================
    // BUSCAR POR ID
    // ================================
    public Cursos buscarPorId(int id) throws Exception {
        String sql = """
                SELECT id_curso, id_carreira, id_area, nome, descricao, tipo_conteudo, data_inicio, status
                FROM cursos WHERE id_curso = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return criarObjeto(rs);
                }
            }
        }

        return null;
    }

    // ================================
    // SALVAR
    // ================================
    public void salvar(Cursos c) throws Exception {
        String sql = """
                INSERT INTO cursos (id_carreira, id_area, nome, descricao, tipo_conteudo, data_inicio, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setarParametros(c, ps);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    c.setIdCurso(keys.getInt(1));
                }
            }
        }
    }

    // ================================
    // ATUALIZAR
    // ================================
    public void atualizar(Cursos c) throws Exception {
        String sql = """
                UPDATE cursos SET
                    id_carreira = ?, id_area = ?, nome = ?, descricao = ?, tipo_conteudo = ?, data_inicio = ?, status = ?
                WHERE id_curso = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setarParametros(c, ps);
            ps.setInt(8, c.getIdCurso());
            ps.executeUpdate();
        }
    }

    // ================================
    // EXCLUIR
    // ================================
    public boolean excluir(int id) throws Exception {
        String sql = "DELETE FROM cursos WHERE id_curso = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ================================
    // MÉTODOS AUXILIARES
    // ================================
    private void setarParametros(Cursos c, PreparedStatement ps) throws Exception {
        ps.setInt(1, c.getIdCarreira());
        ps.setInt(2, c.getIdArea());
        ps.setString(3, c.getNome());
        ps.setString(4, c.getDescricao());
        ps.setString(5, c.getTipoConteudo());
        ps.setDate(6, new java.sql.Date(c.getDataInicio().getTime()));
        ps.setString(7, c.getStatus());
    }

    private Cursos criarObjeto(ResultSet rs) throws Exception {
        Cursos c = new Cursos();

        c.setIdCurso(rs.getInt("id_curso"));
        c.setIdCarreira(rs.getInt("id_carreira"));
        c.setIdArea(rs.getInt("id_area"));
        c.setNome(rs.getString("nome"));
        c.setDescricao(rs.getString("descricao"));
        c.setTipoConteudo(rs.getString("tipo_conteudo"));
        c.setDataInicio(rs.getDate("data_inicio"));
        c.setStatus(rs.getString("status"));

        return c;
    }
}
