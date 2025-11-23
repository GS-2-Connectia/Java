package br.com.Connectia.dao;

import br.com.Connectia.model.Curso;
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

    // ================================
    // LISTAR
    // ================================
    public List<Curso> listar() throws Exception {
        List<Curso> lista = new ArrayList<>();

        String sql = """
                SELECT nm_curso, id_curso, ds_curso, tp_conteudo, dt_inicio, sts_curso, id_usuario, id_area
                FROM T_CON_CURSOS
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
    public Curso buscarPorId(int id) throws Exception {
        String sql = """
                SELECT nm_curso, id_curso, ds_curso, tp_conteudo, dt_inicio, sts_curso, id_usuario, id_area
                FROM T_CON_CURSOS WHERE id_curso = ?
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
    public void salvar(Curso c) throws Exception {
        String sql = """
                INSERT INTO T_CON_CURSOS (nm_curso, id_curso, ds_curso, tp_conteudo, dt_inicio, sts_curso, id_usuario, id_area)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
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
    public void atualizar(Curso c) throws Exception {
        String sql = """
                UPDATE T_CON_CURSOS SET
                    nm_curso = ?, id_curso = ?, ds_curso = ?, tp_conteudo = ?, dt_inicio ?, sts_curso = ?, id_usuario = ?, id_area = ?
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
        String sql = "DELETE FROM T_CON_CURSOS WHERE id_curso = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ================================
    // INSCRIÇÃO/CANCELAMENTO DE USUÁRIO
    // ================================

    // Inscrever usuário na área (ativa ou insere)
    public boolean inscreverUsuarioNaArea(int idUsuario, int idArea) throws Exception {
        String sql = "UPDATE T_CON_AREA_USU SET ST_ATIVO = 'A', DT_INICIO = SYSDATE " +
                "WHERE ID_USUARIO = ? AND ID_AREA = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idArea);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas == 0) {
                // Inserir se não existir
                sql = "INSERT INTO T_CON_AREA_USU (ID_USUARIO, ID_AREA, ST_ATIVO, DT_INICIO) " +
                        "VALUES (?, ?, 'A', SYSDATE)";
                try (PreparedStatement psInsert = conn.prepareStatement(sql)) {
                    psInsert.setInt(1, idUsuario);
                    psInsert.setInt(2, idArea);
                    return psInsert.executeUpdate() > 0;
                }
            }

            return true;
        }
    }

    // Cancelar inscrição do usuário na área
    public boolean cancelarInscricaoNaArea(int idUsuario, int idArea) throws Exception {
        String sql = "UPDATE T_CON_AREA_USU SET ST_ATIVO = 'I' " +
                "WHERE ID_USUARIO = ? AND ID_AREA = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idArea);

            return ps.executeUpdate() > 0;
        }
    }

    // ================================
    // MÉTODOS AUXILIARES
    // ================================
    private void setarParametros(Curso c, PreparedStatement ps) throws Exception {
        ps.setInt(1, c.getIdCarreira());
        ps.setInt(2, c.getIdArea());
        ps.setString(3, c.getNome());
        ps.setString(4, c.getDescricao());
        ps.setString(5, c.getTipoConteudo());
        ps.setDate(6, new java.sql.Date(c.getDataInicio().getTime()));
        ps.setString(7, c.getStatus());
    }

    private Curso criarObjeto(ResultSet rs) throws Exception {
        Curso c = new Curso();

        c.setIdCurso(rs.getInt("nm_curso"));
        c.setIdCarreira(rs.getInt("id_curso"));
        c.setIdArea(rs.getInt("ds_curso"));
        c.setNome(rs.getString("tp_conteudo"));
        c.setDescricao(rs.getString("dt_inicio"));
        c.setTipoConteudo(rs.getString("sts_curso"));
        c.setDataInicio(rs.getDate("id_usuario"));
        c.setStatus(rs.getString("id_area"));

        return c;
    }
}
