package com.mmm.model;
import jakarta.persistence.*; // traz todas as ferramentas necessárias para que o java


@Entity // Esta classe deve virar uma tabela no banco
public class Apoio {
    @Id // chave primaria
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //banco gera id automaticamente
private Long id;

    // obrigatório
    public Apoio(){

    }

@ManyToOne
    @JoinColumn(name = "usuario_id")

private Usuario usuario;
// quem deu apoio


@ManyToOne
    @JoinColumn(name = "problema_id")
    private Problema problema;
//qual problema  foi dado o apoio


    public Apoio (Usuario usuario,Problema problema){
    this.usuario= usuario;
    this.problema=problema;
}

public Long getId() {
    return id;
}

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario(){
     return usuario;
}

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Problema getProblema(){
    return problema;

}

public void setProblema (Problema problema){
    this.problema = problema;


}

}
