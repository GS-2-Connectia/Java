package Connectia.bo;

import Connectia.dao.UsuarioDao;
import Connectia.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UsuarioBo {

    @Inject
    UsuarioDao dao;

    // ================================
    // SALVAR
    // ================================
    public void salvar(Usuario u) throws Exception {

        if (u == null) {
            throw new Exception("Dados do usuário não enviados.");
        }
        if (u.getNome() == null || u.getNome().isBlank()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (u.getEmail() == null || u.getEmail().isBlank()) {
            throw new Exception("O e-mail é obrigatório.");
        }
        if (!u.getEmail().contains("@")) {
            throw new Exception("E-mail inválido.");
        }
        if (u.getSenha() == null || u.getSenha().isBlank()) {
            throw new Exception("A senha é obrigatória.");
        }

        // regra: e-mail não pode existir
        if (dao.buscarPorEmail(u.getEmail()) != null) {
            throw new Exception("Este e-mail já está cadastrado.");
        }

        dao.salvar(u);
    }

    // ================================
    // LISTAR
    // ================================
    public List<Usuario> listar() throws Exception {
        return dao.listar();
    }

    // ================================
    // BUSCAR POR ID
    // ================================
    public Usuario buscarPorId(int id) throws Exception {
        Usuario u = dao.buscarPorId(id);

        if (u == null) {
            throw new Exception("Usuário não encontrado.");
        }

        return u;
    }

    // ================================
    // ATUALIZAR
    // ================================
    public void atualizar(Usuario u) throws Exception {

        if (u == null || u.getIdUsuario() <= 0) {
            throw new Exception("Usuário inválido para atualização.");
        }

        Usuario existente = dao.buscarPorId(u.getIdUsuario());
        if (existente == null) {
            throw new Exception("Usuário não encontrado.");
        }

        if (u.getNome() == null || u.getNome().isBlank()) {
            throw new Exception("O nome é obrigatório.");
        }
        if (u.getEmail() == null || u.getEmail().isBlank()) {
            throw new Exception("O e-mail é obrigatório.");
        }

        dao.atualizar(u);
    }

    // ================================
    // EXCLUIR
    // ================================
    public void excluir(int id) throws Exception {
        Usuario u = dao.buscarPorId(id);

        if (u == null) {
            throw new Exception("Usuário não encontrado.");
        }

        if (!dao.excluir(id)) {
            throw new Exception("Erro ao excluir usuário.");
        }
    }
}
