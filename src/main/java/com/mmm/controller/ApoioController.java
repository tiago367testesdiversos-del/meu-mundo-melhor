package com.mmm.controller;

// importa a classe apoio ( Estrutura de dados)
import com.mmm.model.Apoio;
// importa o Service ( onde fica a lógica do sistema)
import com.mmm.service.ApoioService;
// importa anotações do Spring Web ( para criar API)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Diz que essa classe é um controler REST ( API que retorna JSON)
// Permite receber requisiçoes HTTP ( GET, POST etc)
@RestController

//Define a rota base da API
//Tudo aqui dentro começa com /apoios
@RequestMapping("/apoios")

//Classe responsável por receber requisições relacionadas ao apoio
public class ApoioController{
// reverencia para o service, onde fica a regra de negócio
    private ApoioService service;


    //Construtor usado para injeção de dependencia
    // o spring automaticamente cria e injeta o Apoio service aqui
    public ApoioController(ApoioService service){
this.service=service;
    }

    //criar apoio tipo curtir
    @PostMapping
    public ResponseEntity<Apoio>apoiar(@RequestBody Apoio apoio){
        //chama o service que faz a validação + salva
    Apoio novoApoio=service.apoiar(apoio);
    //retorna resposta http 200 ok com apoio cirado
        return ResponseEntity.ok(novoApoio);
    }

    //Contar apoios por problema
    @GetMapping ("/problema/{id}/count")
        public ResponseEntity<Long>contar(@PathVariable Long id){
        Long total = service.contarPorProblema(id);
        //chama o service que faz count no banco
        return ResponseEntity.ok(total);

    }

}


// “Recebo pedidos da internet, envio pro service, e devolvo resposta”
