package com.mmm.dto;
// DTO de entrada
public class ProblemaRequest {

    private String titulo;
    private String descricao;
    private Long usuarioId;
    private String bairro;
    private String cidade;


    public String getTitulo( ) {
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao=descricao;
    }
public Long getUsuarioId(){
        return usuarioId;

}

public String getBairro(){
        return bairro;
    }
    public void setBairro(String bairro){
        this.bairro=bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUsuarioId(Long usuarioId){
        this.usuarioId=usuarioId;
}


}

// Data transfer Object
//Objeto usado para transportar dados entre camadas (ou cliente e API)
// Sem DTO manda a entidade inteira
//Com DTO manda só os dados necessários

// Nunca misture DTO com model
// model=banco
//dto=entrada/saida de API