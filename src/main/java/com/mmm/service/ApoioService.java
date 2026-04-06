package com.mmm.service;
import com.mmm.model.Apoio;
import com.mmm.model.Problema;
import com.mmm.model.Usuario;

import com.mmm.repository.ApoioRepository;
import com.mmm.repository.ProblemaRepository;
import com.mmm.repository.UsuarioRepository;


import org.springframework.stereotype.Service;

@Service
public class ApoioService {

    private ApoioRepository apoioRepository;
    private UsuarioRepository usuarioRepository;
    private ProblemaRepository problemaRepository;

    // contrutor para injeção de dependência
    public ApoioService (ApoioRepository apoioRepository,
                         UsuarioRepository usuarioRepository,
                         ProblemaRepository problemaRepository

                         ) {
        this.apoioRepository = apoioRepository;
        this.usuarioRepository=usuarioRepository;
        this.problemaRepository=problemaRepository;
    }
// Metodo para registrar um novo apoio a um problema

public Apoio apoiar (Apoio apoio){
    // validação: garantir que veio usuário e problema no JSON
    if(apoio.getUsuario()==null||apoio.getProblema()==null){
    throw new RuntimeException("usuario e Problema são obrigatórios");
    }

    // extrair os Ids para validação
    Long usuarioId = apoio.getUsuario().getId();
    Long problemaId= apoio.getProblema().getId();

//garantir que IDs não são null
    if(usuarioId==null||problemaId==null) {
        throw new RuntimeException("usuarioID e probleamaID são obrigatórios");
    }
// impedir apoio duplicado
    boolean jaExiste = apoioRepository.existsByUsuarioIdAndProblemaId(usuarioId,problemaId);

    if(jaExiste){
        throw new RuntimeException("Usuario já apoiou esse problema!");

    }

    // buscar entidades reais no banco ( Importante JPA precisa saber disso)
    Usuario usuario = usuarioRepository.findById((usuarioId))
            .orElseThrow(()->new RuntimeException("Usuário não encontrato"));

    Problema problema=problemaRepository.findById(problemaId)
            .orElseThrow(()-> new RuntimeException("Problema não encontrato"));

    //vincular os objetos reais ao novo apoio
    apoio.setUsuario(usuario);
    apoio.setProblema(problema);

    //Salvar o apoio validado
    return apoioRepository.save(apoio);

}
public long contarPorProblema(Long problemaId){
        return apoioRepository.countByProblemaId(problemaId);

}

}
