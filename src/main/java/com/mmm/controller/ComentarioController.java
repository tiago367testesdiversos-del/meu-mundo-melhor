package com.mmm.controller;

import com.mmm.model.Comentario;
import com.mmm.dto.ComentarioRequest;
import com.mmm.dto.ComentarioResponse;
import com.mmm.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> criar(@RequestBody ComentarioRequest dto){
        try {
            Comentario comentario = service.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/problema/{id}")
    public List<ComentarioResponse> listarPorProblema(@PathVariable Long id){
        return service.listarPorProblema(id);
    }
}
