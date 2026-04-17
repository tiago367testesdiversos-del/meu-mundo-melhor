package com.mmm.controller;

import com.mmm.dto.ProblemaRequest;
import com.mmm.model.Apoio;
import com.mmm.service.ApoioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apoios")
@CrossOrigin(origins = "*")
public class ApoioController {

    private final ApoioService service;

    public ApoioController(ApoioService service) {
        this.service = service;
    }

    // =========================================================================
    // CRIAR APOIO
    // ----------------------------------------------------------------------------
    // Recebe do front apenas usuarioId e problemaId.
    // Depois chama o service para validar e salvar.
    // =========================================================================
    @PostMapping
    public ResponseEntity<?> apoiar(@RequestBody ProblemaRequest.ApoioRequest request) {
        try {
            Apoio novoApoio = service.apoiar(request.getUsuarioId(), request.getProblemaId());
            return ResponseEntity.status(HttpStatus.CREATED).body(novoApoio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // =========================================================================
    // CONTAR APOIOS DE UM PROBLEMA
    // =========================================================================
    @GetMapping("/problema/{id}/count")
    public ResponseEntity<Long> contar(@PathVariable Long id) {
        Long total = service.contarPorProblema(id);
        return ResponseEntity.ok(total);
    }
}