package br.com.Connectia.dto;

import jakarta.validation.constraints.NotNull;

public class CursoInscricaoDto {

    @NotNull(message = "O id do usuário é obrigatório")
    private Integer idUsuario;

    @NotNull(message = "O id do curso é obrigatório")
    private Integer idCurso;


    // Getters e Setters

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }


}
