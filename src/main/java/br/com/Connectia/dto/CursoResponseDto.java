package br.com.Connectia.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CursoResponseDto {

    private int idCurso;
    private String nome;
    private String descricao;
    private String tipoConteudo;
    private LocalDate dataInicio;
    private String status;

    private String nomeCarreira;
    private String nomeArea;

    // Getters e Setters

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipoConteudo() { return tipoConteudo; }
    public void setTipoConteudo(String tipoConteudo) { this.tipoConteudo = tipoConteudo; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNomeCarreira() { return nomeCarreira; }
    public void setNomeCarreira(String nomeCarreira) { this.nomeCarreira = nomeCarreira; }

    public String getNomeArea() { return nomeArea; }
    public void setNomeArea(String nomeArea) { this.nomeArea = nomeArea; }
}

