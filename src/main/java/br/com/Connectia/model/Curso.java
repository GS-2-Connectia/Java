package br.com.Connectia.model;

import java.util.Date;

public class Curso {

    private int idCurso;
    private String nome;
    private String descricao;
    private String tipoConteudo;
    private Date dataInicio;
    private String status;
    private int idUsuario;
    private int idArea;

    // Construtor vazio (correto)
    public Curso() {
    }

    // Construtor completo
    public Curso(int idCurso, String nome, String descricao, String tipoConteudo,
                 Date dataInicio, String status, int idUsuario, int idArea) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoConteudo = tipoConteudo;
        this.dataInicio = dataInicio;
        this.status = status;
        this.idUsuario = idUsuario;
        this.idArea = idArea;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNomeCurso(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricaoCurso(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusCurso(String status) {
        this.status = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
}
