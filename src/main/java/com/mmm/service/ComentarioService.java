package com.mmm.service;

import com.mmm.dto.ComentarioRequest;
import com.mmm.dto.ComentarioResponse;
import com.mmm.model.Comentario;
import com.mmm.model.TipoUsuario;
import com.mmm.model.Usuario;
import com.mmm.model.Problema;
import com.mmm.repository.ComentarioRepository;
import com.mmm.repository.UsuarioRepository;
import com.mmm.repository.ProblemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProblemaRepository problemaRepository;

    public ComentarioService(ComentarioRepository comentarioRepository,
                             UsuarioRepository usuarioRepository,
                             ProblemaRepository problemaRepository) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.problemaRepository = problemaRepository;
    }


    // LISTAR COMENTÁRIOS POR PROBLEMA

    public List<ComentarioResponse> listarPorProblema(Long problemaId) {
        return comentarioRepository.findByProblemaOrdenado(problemaId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    // SALVAR NOVO COMENTÁRIO

    public Comentario salvar(ComentarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Problema problema = problemaRepository.findById(dto.getProblemaId())
                .orElseThrow(() -> new RuntimeException("Problema não encontrado"));

        boolean isPrefeitura = usuario.getTipo() == TipoUsuario.PREFEITURA;

        if (isPrefeitura) {
            boolean jaExisteOficial = comentarioRepository
                    .existsByProblemaId_AndOficialTrue(problema.getId());

            if (jaExisteOficial) {
                throw new RuntimeException(
                        "Este problema já possui um comentário oficial da prefeitura."
                );
            }
        }

        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setUsuario(usuario);
        comentario.setProblema(problema);
        comentario.setOficial(isPrefeitura);

        return comentarioRepository.save(comentario);
    }


    // CONVERTER ENTIDADE PARA DTO

    private ComentarioResponse toResponse(Comentario comentario) {
        ComentarioResponse response = new ComentarioResponse();

        response.setId(comentario.getId());
        response.setTexto(comentario.getTexto());
        response.setOficial(comentario.isOficial());
        response.setDataCriacao(comentario.getDataCriacao());

        response.setUsuarioNome(comentario.getUsuario().getNome());
        response.setUsuarioTipo(comentario.getUsuario().getTipo().name());

        return response;
    }
}







