package com.mmm.repository;

import com.mmm.model.Apoio;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApoioRepository extends JpaRepository<Apoio,Long> {
    long countByProblemaId (Long problemaId);
    //cria automaticamente SELEC COUNT (*)FROM apoio WERE problema_id=?
    //sem escrever SQL

boolean existsByUsuarioIdAndProblemaId(Long usuarioId, Long problemaId);
// cria automaticamente: SELECT COUNT(*)>0
    // FROM apoio
    //WERE usuario_id?AND problema_id=?
    // sem escrever SQL
}




//