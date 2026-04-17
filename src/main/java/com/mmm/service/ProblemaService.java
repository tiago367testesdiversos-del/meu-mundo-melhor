package com.mmm.service;

import com.mmm.dto.ProblemaRequest;
import com.mmm.dto.ProblemaResponse;
import com.mmm.model.Problema;
import com.mmm.model.Usuario;
import com.mmm.model.TipoUsuario;
import com.mmm.repository.ComentarioRepository;
import com.mmm.repository.ProblemaRepository;
import com.mmm.repository.UsuarioRepository;
import com.mmm.repository.ApoioRepository; // import do apoio

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.time.LocalDateTime;
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




    @Autowired // Spring, injete automaticamente essa dependência pra mim”

    //Ele cria todos os serviços e repositórios
    //Ele guarda eles no “container” (Spring Context)
    //Quando vê @Autowired, ele:
    //procura o objeto correspondente
    //injeta automaticamente ali

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
//prefeitura não pode criar problemas
        if (usuario.getTipo()==TipoUsuario.PREFEITURA){
    throw new ResponseStatusException(
            HttpStatus.FORBIDDEN, //403 CORRETO

            "Usuário prefeitura não pode criar problemas");
}


        // cria a Model ( Problema)
        // Bloqueio de duplicidade por titulo+cidade+bairro
        // Evita múltiplos registros do mesmo problema
        boolean existeDuplicado = problemaRepository
                .existsByTituloAndUfAndCidadeAndBairro(
                        dto.getTitulo(),
                        dto.getUf(),
                        dto.getCidade(),
                        dto.getBairro()
                );
        // se já existir, bloqueia criação

        if ( existeDuplicado){
            throw  new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "ja existe um problema com o mesmo título nessa cidade e bairro"
            );
        }

        Problema problema = new Problema();
        problema.setTitulo(dto.getTitulo());
        problema.setDescricao(dto.getDescricao());
        problema.setBairro(dto.getBairro());
        problema.setCidade(dto.getCidade());
        problema.setUf(dto.getUf());
        problema.setUsuario(usuario);
        problema.setDataCriacao(java.time.LocalDateTime.now()); // importante para ordenação por mais recentes

// salva no banco
        problema = problemaRepository.save(problema);
        //converter para DTO de resposta
        return toResponse(problema);
    }



    //Listar já convertendo para response
    // feed otimzado
     public List<ProblemaResponse>listar(Pageable pageable) {

        // traz uma pagina
        // aqui ordena por data
         return problemaRepository.findAllByOrderByDataCriacaoDesc(pageable)
                 .stream()
                 .map(this::toResponse)
                 .collect(Collectors.toUnmodifiableList());
     }


// essa parte abaixo pega o problema ( entidade do banco)
    // e transforma em `ProblemaResponse ) o que o fornt vê


     //metodo auxiliar ( evita repetição de código)
    //conversão para DTO
private ProblemaResponse toResponse(Problema problema){
    ProblemaResponse response =new ProblemaResponse();
// dados basicos do problema
         response.setId(problema.getId());
         response.setTitulo(problema.getTitulo());
         response.setDescricao(problema.getDescricao());
    response.setUf(problema.getUf());
    response.setCidade(problema.getCidade());
    response.setBairro(problema.getBairro());

    response.setDataCriacao(problema.getDataCriacao()); // colocar data da criação do problema no feed

         // usuário dono do problema
         response.setNomeUsuario(problema.getUsuario().getNome());
//conta os apoios direto no banco ( forma correta)//
         long totalApoios= apoioRepository.countByProblemaId(problema.getId());
response.setTotalApoios(totalApoios);
long totalComentarios=comentarioRepository.countByProblemaId(problema.getId());
response.setTotalComentarios(totalComentarios);
         return response;

         //lembrar:
    //
    //Problema = dados fixos (titulo, descrição, cidade)
    //toResponse = monta o que o front precisa ver
    //countBy... = dados dinâmicos calculados
}

}


// vai aparece primeiro problema no feed de acordo com a data de criação, criado mais recente aparece primeiro


//Agora:
//Busca o usuário real no banco
//Garante integridade
//Evita erro e fraude

//.stream()         → começa processamento
//.map(...)         → transforma cada item
//.collect(...)     → junta tudo de novo

// por enquanto, não vou corrigir problema que pode dar o n+1
// projeto pequeno

//Como resolver, caso eu vá fazer isso:
//

//
//1. JOIN + COUNT (SQL direto) - não
//2. DTO no repository (mais comum)- acho mais fácil
//3. @Query otimizado- talvez