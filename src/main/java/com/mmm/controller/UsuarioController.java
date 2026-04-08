package com.mmm.controller;
import com.mmm.dto.LoginRequest;
import com.mmm.dto.UsuarioRequest;
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


//Recebe UsuarioRequest ( que tem o código secreto)
    @PostMapping // criar usuários
    public Usuario criar(@RequestBody UsuarioRequest dto){
        return service.criar(dto);
    }


    @GetMapping // lista usuários
    public List<Usuario> Listar(){
        return service.listar();

    }
    // login simples
    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request){
        return service.login(request.getEmail(),request.getSenha());
    }
//endpoint PUT (atualizar usuário)
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody UsuarioRequest dto){
        return service.atualizar(id, dto);
    }

}

