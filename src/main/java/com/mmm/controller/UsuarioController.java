package com.mmm.controller;

import com.mmm.model.Usuario;
import com.mmm.service.UsuarioService;
import org.springframework.web.bind.annotation.*; // Esse import traz anotações que transformam sua classe em uma API web.

import java.util.List;

@RestController // Essa classe recebe requisições HTTP (internet) e responde com JSON
@RequestMapping("/usuarios") // define caminho principal

public class UsuarioController {
    private UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service=service;
    }

    // criar usuário

    @PostMapping // criar usuários

    public Usuario criar(@RequestBody Usuario usuario){
        return service.salvar(usuario);
    }
    @GetMapping // lista usuários
    public List<Usuario> Listar(){
        return service.listar();

    }
}

