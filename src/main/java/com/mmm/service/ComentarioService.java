package com.mmm.service;
import com.mmm.dto.ComentarioRequest;
import com.mmm.model.Comentario;
import com.mmm.model.TipoUsuario;
import com.mmm.model.Usuario;
import com.mmm.model.Problema;
import com.mmm.repository.ComentarioRepository;
import com.mmm.repository.UsuarioRepository;// buscar usuário
import com.mmm.repository.ProblemaRepository;// buscar problema
import org.springframework.stereotype.Service;
import java.util.List;



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

    // Listar comentários por problema
    // já vem ordenado do banco
    //prefeitura primeiro, depois mais recente

    public List<Comentario> listarPorProblema(Long problemaId) {
return comentarioRepository.findByProblemaOrdenado(problemaId);

    }
    //metodo para salvar novo comentário
        public Comentario salvar (ComentarioRequest dto){
        // busca usuário pelo Id
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(()->new RuntimeException("Usuário Não encontrato"));
            // busca o problema pelo Id
            Problema problema= problemaRepository.findById(dto.getProblemaId())
                    .orElseThrow(()->new RuntimeException("Problema não encontrado"));

            // regra do do usuário tipo prefeitura
            //se for prefeitura
            //comentário oficial
            // só pode existir 1 por problema

            boolean isPrefeitura = usuario.getTipo()== TipoUsuario.PREFEITURA;

            if(isPrefeitura){
                //bloqueia mais de um comentário oficial por problema
                boolean jaExisteOficial = comentarioRepository
                        .existsByProblemaId_AndOficialTrue(problema.getId());

                if (jaExisteOficial){
                    throw new RuntimeException(
                            "já existe comentário oficial da prefeitura para este problema"
                    );
                }

            }




// cria o comentário
            Comentario comentario = new Comentario();
            comentario.setTexto(dto.getTexto());
            comentario.setUsuario(usuario);//seta objeto
            comentario.setProblema(problema);//seta objeto problema

            // define se é oficial ou não
            comentario.setOficial(isPrefeitura);
            // salva no banco

return comentarioRepository.save(comentario);

                }
            }








