package com.mmm.model;

import jakarta.persistence.*;
@Entity

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 private String nome;
 private String email;
 private String senha;
 private String cidade;

 @Enumerated(EnumType.STRING)
 private TipoUsuario tipo;

// construtor vazio ( obrigatório para o JPA/Hibernate)
    public Usuario (){

    }
    // construtor sem ID (mais usado)-Banco de dados cria o ID pra mim


 public Usuario (String nome, String email, String senha, String cidade,TipoUsuario tipo) {

     this.nome = nome;
     this.email = email;
     this.senha = senha;
     this.cidade = cidade;
     this.tipo=tipo;
 }
public long getId (){
     return id;
 }
public void  setId (Long id){
     this.id=id;
 }

    public String getNome(){ return nome;
 }
    public void setNome(String nome) {
     this.nome = nome;
 }

    public String getEmail(){
return email;
}

public void setEmail(String email){
     this.email=email;
}

public String getSenha(){
     return senha;
 }

public void setSenha(String senha){
     this.senha=senha;
}
public String getCidade(){
     return cidade;
}
public void setCidade(String cidade){
     this.cidade=cidade;
 }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}








