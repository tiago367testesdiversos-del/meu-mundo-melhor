package com.mmm.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Comentario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    private Usuario usuario;

// problema relacionado ao comentario
@ManyToOne
    @JoinColumn(name="problema_id")
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
}