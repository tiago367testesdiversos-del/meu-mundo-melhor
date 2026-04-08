package com.mmm.dto;
import com.mmm.model.TipoUsuario;

public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
    private String cidade;

    private String codigoPrefeitura;

    private TipoUsuario tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigoPrefeitura() {
        return codigoPrefeitura;
    }

    public void setCodigoPrefeitura(String codigoPrefeitura) {
        this.codigoPrefeitura = codigoPrefeitura;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }


}

// criar codigo secreto para criar o usuário prefeitura, será enviado a prefeituras
// quando criarem login
