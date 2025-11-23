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
                SELECT id_curso, nm_curso, ds_curso, tp_conteudo, dt_inicio, sts_curso, id_usuario, id_area
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
                SELECT id_curso, nm_curso, ds_curso, tp_conteudo, dt_inicio, sts_curso, id_usuario, id_area
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
                INSERT INTO T_CON_CURSOS 
                (nm_curso, ds_curso, tp_conteudo, dt_inicio, sts_curso, id_usuario, id_area)
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
    public void atualizar(Curso c) throws Exception {
        String sql = """
                UPDATE T_CON_CURSOS SET
                    nm_curso = ?, 
                    ds_curso = ?, 
                    tp_conteudo = ?, 
                    dt_inicio = ?, 
                    sts_curso = ?, 
                    id_usuario = ?, 
                    id_area = ?
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


    // ----------------------------------------------------------------------------------
    //  INSCRIÇÃO DO USUÁRIO NO CURSO  (agora está correto)
    // ----------------------------------------------------------------------------------

    public boolean inscreverUsuarioNoCurso(int idUsuario, int idCurso) throws Exception {
        String sql = """
            UPDATE T_CON_CURSO_USUARIO 
            SET ST_ATIVO = 'A', DT_INSCRICAO = SYSDATE
            WHERE ID_USUARIO = ? AND ID_CURSO = ?
            """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idCurso);

            int linhas = ps.executeUpdate();

            if (linhas == 0) {
                sql = """
                        INSERT INTO T_CON_CURSO_USUARIO 
                        (ID_USUARIO, ID_CURSO, ST_ATIVO, DT_INSCRICAO)
                        VALUES (?, ?, 'A', SYSDATE)
                      """;

                try (PreparedStatement insert = conn.prepareStatement(sql)) {
                    insert.setInt(1, idUsuario);
                    insert.setInt(2, idCurso);
                    return insert.executeUpdate() > 0;
                }
            }

            return true;
        }
    }


    // ----------------------------------------------------------------------------------
    //  CANCELAR INSCRIÇÃO
    // ----------------------------------------------------------------------------------

    public boolean cancelarInscricaoNoCurso(int idUsuario, int idCurso) throws Exception {
        String sql = """
                UPDATE T_CON_CURSO_USUARIO 
                SET ST_ATIVO = 'I'
                WHERE ID_USUARIO = ? AND ID_CURSO = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setInt(2, idCurso);

            return ps.executeUpdate() > 0;
        }
    }


    // ================================
    // AUXILIARES
    // ================================
    private void setarParametros(Curso c, PreparedStatement ps) throws Exception {
        ps.setString(1, c.getNome());
        ps.setString(2, c.getDescricao());
        ps.setString(3, c.getTipoConteudo());
        ps.setDate(4, new java.sql.Date(c.getDataInicio().getTime()));
        ps.setString(5, c.getStatus());
        ps.setInt(6, c.getIdUsuario());
        ps.setInt(7, c.getIdArea());
    }

    private Curso criarObjeto(ResultSet rs) throws Exception {
        Curso c = new Curso();

        c.setIdCurso(rs.getInt("id_curso"));
        c.setNome(rs.getString("nm_curso"));
        c.setDescricao(rs.getString("ds_curso"));
        c.setTipoConteudo(rs.getString("tp_conteudo"));
        c.setDataInicio(rs.getDate("dt_inicio"));
        c.setStatus(rs.getString("sts_curso"));
        c.setIdUsuario(rs.getInt("id_usuario"));
        c.setIdArea(rs.getInt("id_area"));

        return c;
    }
}
