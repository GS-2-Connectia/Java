package Connectia.model;

public class Conteudo {
    private int idConteudo;
    private int idCurso;
    private int idCarreira;
    private int idArea;
    private String titulo;
    private String tipo;

    public void Conteudo() {
    }

    public void Conteudo(int idConteudo, int idCurso, int idCarreira, int idArea, String titulo, String tipo) {
        this.idConteudo = idConteudo;
        this.idCurso = idCurso;
        this.idCarreira = idCarreira;
        this.idArea = idArea;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public int getIdConteudo() {
        return idConteudo;
    }

    public void setIdConteudo(int idConteudo) {
        this.idConteudo = idConteudo;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
