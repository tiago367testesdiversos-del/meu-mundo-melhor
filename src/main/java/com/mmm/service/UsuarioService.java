package com.mmm.service;

import com.mmm.dto.UsuarioRequest;
import com.mmm.model.Usuario;
import com.mmm.model.TipoUsuario;

import com.mmm.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    //Injeção via construtor(POO correto)
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    //Salva usuário

    public Usuario salvar(Usuario usuario) {
        // Se o usuário enviar tipo, define automáticamente como COMUM
        if (usuario.getTipo() == null) {
            usuario.setTipo(TipoUsuario.COMUM);
        }


        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario criar(UsuarioRequest dto) {

        // impede email duplicado
        if (repository.existsByEmail(dto.getEmail().trim().toLowerCase())) {
            throw new RuntimeException("Já existe um usuário cadastrado com esse email.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCidade(dto.getCidade());

        // código da prefeitura, para criar o UsuarioPrefeitura
        if ("123456".equals(dto.getSenha())) {
            usuario.setTipo(TipoUsuario.PREFEITURA);
        } else {
            usuario.setTipo(TipoUsuario.COMUM);
        }
        return repository.save(usuario);


    }
    public Usuario login(String email, String senha){
        return repository.findByEmailAndSenha(email, senha)
                .orElseThrow(()->new RuntimeException("Email ou senha invalidos"));
    }




    //busca usuário no banco
    //altera dados
    //salva de novo
    public Usuario atualizar(Long id, UsuarioRequest dto){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return repository.save(usuario);



    }


}
//
// Repository = banco
// Service = Regras

