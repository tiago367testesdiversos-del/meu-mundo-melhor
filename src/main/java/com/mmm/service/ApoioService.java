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

    private final ApoioRepository apoioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProblemaRepository problemaRepository;

    // =========================================================================
    // CONSTRUTOR PARA INJEÇÃO DE DEPENDÊNCIA
    // =========================================================================
    public ApoioService(
            ApoioRepository apoioRepository,
            UsuarioRepository usuarioRepository,
            ProblemaRepository problemaRepository
    ) {
        this.apoioRepository = apoioRepository;
        this.usuarioRepository = usuarioRepository;
        this.problemaRepository = problemaRepository;
    }


    // REGISTRAR NOVO APOIO
    // ----------------------------------------------------------------------------
    // Recebe apenas os IDs.
    // Busca usuário e problema reais no banco.
    // Impede apoio duplicado.
    // Salva e retorna o apoio.
    // =========================================================================
    public Apoio apoiar(Long usuarioId, Long problemaId) {

        // validação básica
        if (usuarioId == null || problemaId == null) {
            throw new RuntimeException("usuarioId e problemaId são obrigatórios");
        }

        // impedir apoio duplicado
        boolean jaExiste = apoioRepository.existsByUsuarioIdAndProblemaId(usuarioId, problemaId);

        if (jaExiste) {
            throw new RuntimeException("Usuário já apoiou esse problema!");
        }

        // buscar entidades reais no banco
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Problema problema = problemaRepository.findById(problemaId)
                .orElseThrow(() -> new RuntimeException("Problema não encontrado"));

        // criar novo apoio
        Apoio apoio = new Apoio();
        apoio.setUsuario(usuario);
        apoio.setProblema(problema);

        // salvar apoio
        return apoioRepository.save(apoio);
    }

    // =========================================================================
    // CONTAR APOIOS POR PROBLEMA
    // =========================================================================
    public long contarPorProblema(Long problemaId) {
        return apoioRepository.countByProblemaId(problemaId);
    }
}
