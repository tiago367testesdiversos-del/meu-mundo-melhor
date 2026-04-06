package com.mmm.service;

import com.mmm.dto.ProblemaRequest;
import com.mmm.dto.ProblemaResponse;
import com.mmm.model.Problema;
import com.mmm.model.Usuario;
import com.mmm.repository.ComentarioRepository;
import com.mmm.repository.ProblemaRepository;
import com.mmm.repository.UsuarioRepository;
import com.mmm.repository.ApoioRepository; // import do apoio

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// serve para transformar e  coletar dados de listas
import java.util.stream.Collectors;


@Service// indica que essa classe é a regra do negócio
public class ProblemaService {

     private final ProblemaRepository problemaRepository;
     // acesso ao banco de dados de problema
     private final UsuarioRepository usuarioRepository;
//acessa banco de dados de usuário
private final ApoioRepository apoioRepository;
// acessa ao banco de dados do apoio
private final ComentarioRepository comentarioRepository;
// acessa banco de dados do comentários
     @Autowired
    public ProblemaService(ProblemaRepository problemaRepository,
                           UsuarioRepository usuarioRepository,
     ApoioRepository apoioRepository,
                           ComentarioRepository comentarioRepository) {
         this.problemaRepository = problemaRepository;// injeta o repository de problema
         this.usuarioRepository = usuarioRepository; // injeta repository de usuário
         this.apoioRepository = apoioRepository;// injeta repository de apoio
         this.comentarioRepository= comentarioRepository;// injeta repository de comentário
     }
    // Metodo novo Usando o dto ( profissional) -
    public ProblemaResponse criar(ProblemaRequest dto) {
        // Busca o usuário e, se não encontrar, lança uma exceção
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
// cria a Model ( Problema)
        Problema problema = new Problema();
        problema.setTitulo(dto.getTitulo());
        problema.setDescricao(dto.getDescricao());
        problema.setBairro(dto.getBairro());
        problema.setCidade(dto.getCidade());
        problema.setUsuario(usuario);
// salva no banco
        problema = problemaRepository.save(problema);
        //converter para DTO de resposta
        return toResponse(problema);
    }
    //Listar já convertendo para response
     public List<ProblemaResponse>listar() {
            return problemaRepository.findAll()
         .stream()// transforma a lista em um fluxo de dados
                 .map(this::toResponse)//Converte cada problema para ProblemaResponse// loop automático
                 .collect(Collectors.toList());// pega o resultado e transforma de volta em lista
     }
     //metodo auxiliar ( evita repetição de código)
private ProblemaResponse toResponse(Problema problema){


         ProblemaResponse response =new ProblemaResponse();
         response.setId(problema.getId());
         response.setTitulo(problema.getTitulo());
         response.setDescricao(problema.getDescricao());
         response.setNomeUsuario(problema.getUsuario().getNome());
//conta os apoios direto no banco ( forma correta)//
         long totalApoios= apoioRepository.countByProblemaId(problema.getId());
response.setTotalApoios(totalApoios);
long totalcomentarios=comentarioRepository.countByProblemaId(problema.getId());
response.setTotalComentarios(totalcomentarios);
         return response;
}

}





//Agora:
//Busca o usuário real no banco
//Garante integridade
//Evita erro e fraude

//.stream()         → começa processamento
//.map(...)         → transforma cada item
//.collect(...)     → junta tudo de novo