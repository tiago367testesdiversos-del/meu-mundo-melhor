package com.mmm.model;
import com.mmm.model.Apoio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList; // evitar null



@Entity // transforma em tabela
// Diz para o Spring Boot + Hibernate:
// "essa classe vira uma tabela no banco"
public class Problema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ID ( chave primária)
    // id ( auto incremento)
    // cada problema tem um ID unico
    // o banco cria automáticamente
    private String titulo;
    private String descricao;
    private String bairro;
    private String cidade;
    // Cada variável vira uma coluna

    // Relacionaomento com Usuário
    @ManyToOne //muitos problemas pertencem a um usuário
    @JoinColumn(name = "usuario_id") // cria coluna usuário_id
    // impede que o spring entre em loop infinito ao ler o usuário
    @JsonIgnoreProperties ({"hibernateteLazyInitializer","handler","senha"})
    private Usuario usuario;



    // Relacionamento com apoios ( tipo "likes")
    @OneToMany(mappedBy = "problema")
    private List<Apoio>apoios = new ArrayList<>();
    //inicializa lista para evitar  nullPointerExcepiton

    //Lugar	Função
    //Model (Problema)	tem a lista de apoios
    //Service (toResponse)	conta os apoios
    //DTO (ProblemaResponse)	mostra o total


    // Toda entidade JPA precisa de um construtor vazio
    public Problema () {

    }

    // Construtor completo
    public Problema(String titulo, String descricao, String bairro, String cidade, Usuario usuario) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.bairro = bairro;
        this.cidade = cidade;
        this.usuario = usuario;
    }
    // ler dados, salvar no banco, converter em JSON
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

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao (String descricao){
        this.descricao=descricao;
    }

    public String getBairro(){
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario( Usuario usuario){
        this.usuario=usuario;
    }

    public List<Apoio>getApoios(){
        return apoios;
    }
    public void setApoios(List<Apoio>apoios){
        this.apoios = apoios;
    }

}

// tabela problema :
// id
//titulo
//descricao
//bairro
//cidade
//usuario_id

// Toda entidade JPA precisa de
//construtor vazio
// getter/setters
