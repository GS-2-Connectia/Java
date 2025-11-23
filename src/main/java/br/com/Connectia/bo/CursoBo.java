package br.com.Connectia.bo;

import br.com.Connectia.dao.CursoDao;
import br.com.Connectia.model.Curso;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CursoBo {

    @Inject
    CursoDao dao;

    // ================================
    // SALVAR
    // ================================
    public void salvar(Curso c) throws Exception {

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
    public List<Curso> listar() throws Exception {
        return dao.listar();
    }

    // ================================
    // BUSCAR POR ID
    // ================================
    public Curso buscarPorId(int id) throws Exception {
        Curso c = dao.buscarPorId(id);

        if (c == null) {
            throw new Exception("Curso não encontrado.");
        }

        return c;
    }

    // ================================
    // ATUALIZAR
    // ================================
    public void atualizar(Curso c) throws Exception {

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
        Curso c = dao.buscarPorId(id);

        if (c == null) {
            throw new Exception("Curso não encontrado.");
        }

        if (!dao.excluir(id)) {
            throw new Exception("Erro ao excluir curso.");
        }
    }
}
