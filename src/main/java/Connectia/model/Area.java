package Connectia.model;

public class Area {

    private int idArea;
    private int idUsuario;

    public Area() {
    }

    public Area (int idArea, int idUsuario) {
        this.idArea = idArea;
        this.idUsuario = idUsuario;
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
}






