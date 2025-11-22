package Connectia.model;

public class Carreira {

    private int idCarreira;
    private String nome;
    private int idArea;
    private String descricao;
    private String tipoCarreira;

    public Carreira() {
    }

    public Carreira(int idCarreira, String nome, int idArea, String descricao, String tipoCarreira) {
        this.idCarreira = idCarreira;
        this.nome = nome;
        this.idArea = idArea;
        this.descricao = descricao;
        this.tipoCarreira = tipoCarreira;
    }

    public int getIdCarreira() {
        return idCarreira;
    }

    public void setIdCarreira(int idCarreira) {
        this.idCarreira = idCarreira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoCarreira() {
        return tipoCarreira;
    }

    public void setTipoCarreira(String tipoCarreira) {
        this.tipoCarreira = tipoCarreira;
    }
}



