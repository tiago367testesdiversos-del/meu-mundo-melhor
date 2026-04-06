package com.mmm.service;

import com.mmm.model.Usuario;
import com.mmm.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    //Injeção via construtor(POO correto)
    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }
    //Salva usuário

    public Usuario salvar (Usuario usuario){
        return repository.save(usuario);
    }
    public List<Usuario>listar(){
        return repository.findAll();
    }


}

// Repository = banco
// Service = Regras

