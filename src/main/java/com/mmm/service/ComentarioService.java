package com.mmm.service;
import com.mmm.dto.ComentarioRequest;
import com.mmm.model.Comentario;
import com.mmm.model.Usuario;
import com.mmm.model.Problema;
import com.mmm.repository.ComentarioRepository;
import com.mmm.repository.UsuarioRepository;// buscar usuário
import com.mmm.repository.ProblemaRepository;// buscar problema
import org.springframework.stereotype.Service;
import java.util.List;

// todos os imports devem ficar antes das anotações

@Service // spring não sabe o que essa classe é um "Service" ao menos que o marque


public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
private final ProblemaRepository problemaRepository;

    public ComentarioService(ComentarioRepository comentarioRepository,
                             UsuarioRepository usuarioRepository,
                             ProblemaRepository problemaRepository){
        this.comentarioRepository=comentarioRepository;
        this.usuarioRepository=usuarioRepository;
        this.problemaRepository=problemaRepository;
    }

    public List<Comentario> listarPorProblema(Long ProblemaId) {
        return comentarioRepository.findByProblemaId(ProblemaId);
    }
    //metodo para salvar
        public Comentario salvar (ComentarioRequest dto){
        // busca usuário pelo Id
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(()->new RuntimeException("Usuário Não encontrato"));
            // busca o problema pelo Id
            Problema problema= problemaRepository.findById(dto.getProblemaId())
                    .orElseThrow(()->new RuntimeException("Problema não encontrado"));
// cria o comentário
            Comentario comentario = new Comentario();
            comentario.setTexto(dto.getTexto());
            comentario.setUsuario(usuario);//seta objeto
            comentario.setProblema(problema);//seta objeto problema
            //salva no banco
return comentarioRepository.save(comentario);

                }
            }








