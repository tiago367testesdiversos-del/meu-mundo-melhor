package com.mmm.dto;

import java.time.LocalDateTime;

public class ProblemaResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private String nomeUsuario;

    private long totalApoios;
    private long totalComentarios;

    private String comentarioOficial;


    private String uf;
    private String cidade;
    private String bairro;

    private LocalDateTime dataCriacao;

    // =========================
    // GETTERS E SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public long getTotalApoios() {
        return totalApoios;
    }

    public void setTotalApoios(long totalApoios) {
        this.totalApoios = totalApoios;
    }

    public long getTotalComentarios() {
        return totalComentarios;
    }

    public void setTotalComentarios(long totalComentarios) {
        this.totalComentarios = totalComentarios;
    }

    public String getComentarioOficial() {
        return comentarioOficial;
    }

    public void setComentarioOficial(String comentarioOficial) {
        this.comentarioOficial = comentarioOficial;
    }

    // =========================
    // NOVOS CAMPOS
    // =========================

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }



}
// por que exitir essa classe
// evitar vazar senha e trazer somente dados necessários

// model = banco
//request = entrada
//response=saida

//Resumo simples (pra nunca esquecer)
//Camada	Usa o quê
//Controller	DTO
//Service	DTO + Model
//Repository	Model
//Banco	Model

///👉 o que o frontend ENVIA