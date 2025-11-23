package br.com.Connectia.dto;

public class ConteudoDto {

    private int idConteudo;
    private String titulo;
    private String tipo;
    private int idCurso;
    private int idArea;

    public int getIdConteudo() { return idConteudo; }
    public void setIdConteudo(int idConteudo) { this.idConteudo = idConteudo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public int getIdArea() { return idArea; }
    public void setIdArea(int idArea) { this.idArea = idArea; }
}
