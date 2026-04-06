package com.mmm.controller;

import com.mmm.service.ProblemaService;
import org.springframework.web.bind.annotation.*;
import com.mmm.dto.ProblemaRequest;
import com.mmm.dto.ProblemaResponse;

import java.util.List;

@RestController
@RequestMapping("/problemas")


public class ProblemaController {

    private final ProblemaService service;

    public ProblemaController(ProblemaService service){
        this.service=service;

    }
    @PostMapping
    public ProblemaResponse criar(@RequestBody ProblemaRequest request) {
        return service.criar(request);
    }
//agora retorno o DTO
    @GetMapping
    public List <ProblemaResponse> listar(){
        return service.listar();
    }


}

// motivo do service usar o problema model e controller não
//service = trabalha com banco( model)
// controler = trabalhar com o mundo externo DTO

// não usar o Model no controller
// evita expor senha ,trazer dados desnecessários
// dar erro de loop infinito ( JSON)
// acoplar API no banco