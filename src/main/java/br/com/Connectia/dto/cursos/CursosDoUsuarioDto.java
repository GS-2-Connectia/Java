package br.com.Connectia.dto.cursos;

import java.util.Date;

public class CursosDoUsuarioDto {

    private int idCurso;
    private String nomeCurso;
    private String status;
    private Date dataInicio;
    private int idArea;

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNomeCurso() { return nomeCurso; }
    public void setNomeCurso(String nomeCurso) { this.nomeCurso = nomeCurso; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }

    public int getIdArea() { return idArea; }
    public void setIdArea(int idArea) { this.idArea = idArea; }
}
