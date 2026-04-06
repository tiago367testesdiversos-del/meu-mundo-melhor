package com.mmm.model;

import jakarta.persistence.*;

import java.security.PublicKey;

@Entity
public class Comentario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String texto;
// Usuario que comentou
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
    //problema relacionado

    //construtor vazio (obrigatório)
    public Comentario (){

    }

@ManyToOne
    @JoinColumn(name="problema_id")
    private Problema problema;



public Comentario(String texto,Usuario usuario,Problema problema){
    this.texto=texto;
    this.usuario=usuario;
    this.problema=problema;
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

}