package com.mmm.dto;
// DTO de entrada
public class ProblemaRequest {

    private String titulo;
    private String descricao;
    private Long usuarioId;
    private String bairro;
    private String cidade;
    private String uf;


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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUsuarioId(Long usuarioId){
        this.usuarioId=usuarioId;
}


    public static class ApoioRequest {

        private Long usuarioId;
        private Long problemaId;

        public Long getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Long usuarioId) {
            this.usuarioId = usuarioId;
        }

        public Long getProblemaId() {
            return problemaId;
        }

        public void setProblemaId(Long problemaId) {
            this.problemaId = problemaId;
        }
    }
}

// Data transfer Object
//Objeto usado para transportar dados entre camadas (ou cliente e API)
// Sem DTO manda a entidade inteira
//Com DTO manda só os dados necessários

// Nunca misture DTO com model
// model=banco
//dto=entrada/saida de API

//o que o backend DEVOLVE

//FRONTEND
//   ↓
//ProblemRequest (DTO entrada)
//   ↓
//SERVICE (regra + validação duplicado)
//   ↓
//ENTITY (salva no banco)
//   ↓
//ProblemResponse (DTO saída)
//   ↓
//FRONTEND (feed)