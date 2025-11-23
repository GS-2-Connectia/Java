package br.com.Connectia.dto.cursos;

import java.util.Date;

public class DetalhesCursoDto {

    private int idCurso;
    private String nomeCurso;
    private String descricaoCurso;
    private String tipoConteudo;
    private Date dataInicio;
    private String statusCurso;
    private int idUsuario;
    private int idArea;

    // Getters e Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNomeCurso() { return nomeCurso; }
    public void setNomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; }

    public String getDescricaoCurso() { return descricaoCurso; }
    public void setDescricaoCurso(String descricaoCurso) { this.descricaoCurso = descricaoCurso; }

    public String getTipoConteudo() { return tipoConteudo; }
    public void setTipoConteudo(String tipoConteudo) { this.tipoConteudo = tipoConteudo; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public String getStatusCurso() { return statusCurso; }
    public void setStatusCurso(String statusCurso) { this.statusCurso = statusCurso; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdArea() { return idArea; }
    public void setIdArea(int idArea) { this.idArea = idArea; }
}
