package com.mmm.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

// essa anotação controla oq eu vai ou não aparece no JSON
//fala pro Spring : ingnora esses campos quando for transformar o objeto em JSON
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comentario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String texto;

// indica se o comentário é oficial ( Feito via prefeitura)
private boolean oficial;

//data criação comentário

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

// Usuario que comentou
    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})// evita loop infinito
    private Usuario usuario;

// problema relacionado ao comentario
@ManyToOne
    @JoinColumn(name="problema_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})// evita loop infinito
    private Problema problema;

    //construtor vazio (obrigatório)
    public Comentario (){

    }

public Comentario(String texto,Usuario usuario,Problema problema, boolean oficial){
    this.texto=texto;
    this.usuario=usuario;
    this.problema=problema;
    this.oficial=oficial;
}
public Long getId() {
    return id;
}
    public String getTexto(){
            return texto;
}
public void setTexto(String texto){
    this.texto = texto;
}
public Usuario getUsuario(){
    return usuario;
}
public void setUsuario(Usuario usuario){
    this.usuario= usuario;
}
public Problema getProblema() {
    return problema;
}
    public void setProblema(Problema problema){
        this.problema = problema;
    }
public boolean isOficial(){
    return oficial;
}

    public void setOficial(boolean oficial) {
        this.oficial = oficial;
    }

    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}