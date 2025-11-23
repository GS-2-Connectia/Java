package br.com.Connectia.model;

public class Area {

    private int idArea;
    private int idUsuario;
    private String descricao;
    private String tipoFormacao;

    public Area() {
    }

    public Area (int idArea, int idUsuario, String descricao, String tipoFormacao) {
        this.idArea = idArea;
        this.idUsuario = idUsuario;
        this.descricao = descricao;
        this.tipoFormacao = tipoFormacao;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoFormacao() {
        return tipoFormacao;
    }

    public void setTipoFormacao(String tipoFormacao) {
        this.tipoFormacao = tipoFormacao;
    }
}






