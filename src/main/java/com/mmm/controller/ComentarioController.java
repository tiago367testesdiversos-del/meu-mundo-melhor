package com.mmm.controller;

import com.mmm.model.Comentario;
import com.mmm.dto.ComentarioRequest;
import com.mmm.dto.ComentarioResponse;
import com.mmm.service.ComentarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    private ComentarioService service;

    public ComentarioController(ComentarioService service){
        this.service = service;
    }

    @PostMapping
    public Comentario criar(@RequestBody ComentarioRequest dto){
        return service.salvar(dto);
    }

    @GetMapping("/problema/{id}")
    public List<ComentarioResponse> listarPorProblema(@PathVariable Long id){
        return service.listarPorProblema(id);
    }
}
