package Connectia.bo;

import Connectia.dao.CursosDao;
import Connectia.model.Cursos;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CursosBo {

    @Inject
    CursosDao dao;

    // ================================
    // SALVAR
    // ================================
    public void salvar(Cursos c) throws Exception {

        if (c == null) {
            throw new Exception("Dados do curso não enviados.");
        }
        if (c.getNome() == null || c.getNome().isBlank()) {
            throw new Exception("O nome do curso é obrigatório.");
        }
        if (c.getDescricao() == null || c.getDescricao().isBlank()) {
            throw new Exception("A descrição é obrigatória.");
        }
        if (c.getTipoConteudo() == null || c.getTipoConteudo().isBlank()) {
            throw new Exception("O tipo de conteúdo é obrigatório.");
        }
        if (c.getDataInicio() == null) {
            throw new Exception("A data de início é obrigatória.");
        }
        if (c.getStatus() == null || c.getStatus().isBlank()) {
            throw new Exception("O status é obrigatório.");
        }

        dao.salvar(c);
    }

    // ================================
    // LISTAR
    // ================================
    public List<Cursos> listar() throws Exception {
        return dao.listar();
    }

    // ================================
    // BUSCAR POR ID
    // ================================
    public Cursos buscarPorId(int id) throws Exception {
        Cursos c = dao.buscarPorId(id);

        if (c == null) {
            throw new Exception("Curso não encontrado.");
        }

        return c;
    }

    // ================================
    // ATUALIZAR
    // ================================
    public void atualizar(Cursos c) throws Exception {

        if (c == null || c.getIdCurso() <= 0) {
            throw new Exception("Curso inválido para atualização.");
        }

        if (dao.buscarPorId(c.getIdCurso()) == null) {
            throw new Exception("Curso não encontrado.");
        }

        if (c.getNome() == null || c.getNome().isBlank()) {
            throw new Exception("O nome do curso é obrigatório.");
        }

        dao.atualizar(c);
    }

    // ================================
    // EXCLUIR
    // ================================
    public void excluir(int id) throws Exception {
        Cursos c = dao.buscarPorId(id);

        if (c == null) {
            throw new Exception("Curso não encontrado.");
        }

        if (!dao.excluir(id)) {
            throw new Exception("Erro ao excluir curso.");
        }
    }
}
