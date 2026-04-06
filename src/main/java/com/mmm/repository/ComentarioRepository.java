package com.mmm.repository;
import com.mmm.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository  extends JpaRepository <Comentario,Long> {
//busca lista de comentários por problema
    List <Comentario> findByProblemaId(long ProblemaId);
    // conta quantos comentários tem um problema
    long countByProblemaId (Long problemaId);
}
