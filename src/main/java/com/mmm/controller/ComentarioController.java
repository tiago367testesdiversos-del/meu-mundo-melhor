package com.mmm.controller;

import com.mmm.model.Comentario;
import com.mmm.dto.ComentarioRequest; //importa DTO
import com.mmm.service.ComentarioService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/comentario")

public class ComentarioController {

    private ComentarioService service;

    public ComentarioController(ComentarioService service){
        this.service=service;

    }


@PostMapping
    public Comentario criar(@RequestBody ComentarioRequest dto){
        //Agora recebe DTO com usuárioId e problemaId
        return service.salvar(dto);
        // chama o metodo correto no service
}
@GetMapping("/problema/{id}")
    public List<Comentario>ListarporProblema(@PathVariable long id){
        return service.listarPorProblema(id);

}



}
