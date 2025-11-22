package Connectia.dao;

import Connectia.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioDao {

    @Inject
    DataSource dataSource;

    // ================================
    // LISTAR
    // ================================
    public List<Usuario> listar() throws Exception {
        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT id_usuario, nome, email, senha, id_carreira, tipo_plano, id_area FROM usuario";

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
    public Usuario buscarPorId(int id) throws Exception {
        String sql = "SELECT id_usuario, nome, email, senha, id_carreira, tipo_plano, id_area FROM usuario WHERE id_usuario = ?";

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
    // BUSCAR POR EMAIL
    // ================================
    public Usuario buscarPorEmail(String email) throws Exception {
        String sql = "SELECT id_usuario, nome, email, senha, id_carreira, tipo_plano, id_area FROM usuario WHERE email = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

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
    public void salvar(Usuario u) throws Exception {
        String sql = """
                INSERT INTO usuario (nome, email, senha, id_carreira, tipo_plano, id_area)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setarParametros(u, ps);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    u.setIdUsuario(keys.getInt(1));
                }
            }
        }
    }

    // ================================
    // ATUALIZAR
    // ================================
    public void atualizar(Usuario u) throws Exception {
        String sql = """
                UPDATE usuario SET
                    nome = ?, email = ?, senha = ?, id_carreira = ?, tipo_plano = ?, id_area = ?
                WHERE id_usuario = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setarParametros(u, ps);
            ps.setInt(7, u.getIdUsuario());
            ps.executeUpdate();
        }
    }

    // ================================
    // EXCLUIR
    // ================================
    public boolean excluir(int id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ================================
    // MÉTODOS AUXILIARES
    // ================================
    private void setarParametros(Usuario u, PreparedStatement ps) throws Exception {
        ps.setString(1, u.getNome());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getSenha());
        ps.setObject(4, u.getCarreira() != null ? u.getCarreira().getIdCarreira() : null);
        ps.setString(5, u.getTipoPlano());
        ps.setObject(6, u.getArea() != null ? u.getArea().getIdArea() : null);
    }

    private Usuario criarObjeto(ResultSet rs) throws Exception {
        Usuario u = new Usuario();

        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        u.setTipoPlano(rs.getString("tipo_plano"));

        // carreira e area são objetos — mas como você pediu para não alterar Model, deixo null
        return u;
    }
}
