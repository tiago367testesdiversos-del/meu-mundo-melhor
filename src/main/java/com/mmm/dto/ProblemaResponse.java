package com.mmm.dto;
//DTO de saída (Response)
//Classe que define o que a API Vai devolver para o cliente ( Postman, fontend etc)
// Diferente da Model (Problema), aqui controla exatamente quais dados serão expostos
public class ProblemaResponse {

private Long id;


    private String titulo;
    //titulo do problema

    private String descricao;
    // descricão do problema

    private String nomeUsuario;
    //Nome do usuário dono do problema
    // importante: Não mandamos o objeto Usuário inteiro, só o necessário
private long totalApoios;

private long totalComentarios;

    //gettes e setters
    // São obrigatórios para o spring conseguir ler os dados JSON

    public long getId(){
        return id;
    }
public void setId (Long id ) {
    this.id = id;
}
        public String getTitulo(){
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
    public String getNomeUsuario(){
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