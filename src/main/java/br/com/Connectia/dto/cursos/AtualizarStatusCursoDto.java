package br.com.Connectia.dto.cursos;

public class AtualizarStatusCursoDto {

    private String novoStatus;
    private int idArea;

    // Getters e Setters
    public String getNovoStatus() { return novoStatus; }
    public void setNovoStatus(String novoStatus) { this.novoStatus = novoStatus; }

    public int getIdArea() { return idArea; }
    public void setIdArea(int idArea) { this.idArea = idArea; }
}
