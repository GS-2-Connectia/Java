package Connectia.model;

import java.util.Date;

public class Cursos {
    private int idCurso;
    private int idCarreira;
    private int idArea;
    private String nome;
    private String descricao;
    private String tipoConteudo;
    private Date dataInicio;
    private String status;

    public void Curso() {
    }

    public void Curso(int idCurso, int idCarreira, int idArea, String nome, String descricao,
                      String tipoConteudo, Date dataInicio, String status) {
        this.idCurso = idCurso;
        this.idCarreira = idCarreira;
        this.idArea = idArea;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoConteudo = tipoConteudo;
        this.dataInicio = dataInicio;
        this.status = status;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdCarreira() {
        return idCarreira;
    }

    public void setIdCarreira(int idCarreira) {
        this.idCarreira = idCarreira;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
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

    public void setStatus(String status) {
        this.status = status;
    }
}


