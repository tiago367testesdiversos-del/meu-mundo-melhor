package com.mmm.controller;

import com.mmm.service.ProblemaService;
import org.springframework.web.bind.annotation.*;
import com.mmm.dto.ProblemaRequest;
import com.mmm.dto.ProblemaResponse;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/problemas")


public class ProblemaController {

    private final ProblemaService service;

    public ProblemaController(ProblemaService service){
        this.service=service;

    }

    // criar problema
    @PostMapping
    public ProblemaResponse criar(@RequestBody ProblemaRequest request) {
        return service.criar(request);
    }



    //Feed otimzado
    @GetMapping
    public List <ProblemaResponse> listar(
        @RequestParam(defaultValue = "0")int page,
                @RequestParam(defaultValue = "10")int size
//page = pagina
        // size= Quantos itens por pagina
        ){
        //retorna por pagina ( produção real)
        Pageable pageable = PageRequest.of(page,size);
        return service.listar(pageable); // conecta com otimizacao no sevice
    }
}




// motivo do service usar o problema model e controller não
//service = trabalha com banco( model)
// controler = trabalhar com o mundo externo DTO

// não usar o Model no controller
// evita expor senha ,trazer dados desnecessários
// dar erro de loop infinito ( JSON)
// acoplar API no banco