package com.mmm.repository;
import com.mmm.model.Comentario;
import com.mmm.model.TipoUsuario; // importante para usar no ORDER BY
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ComentarioRepository  extends JpaRepository <Comentario,Long> {
//busca lista de comentários por problema
    List <Comentario> findByProblemaId(long ProblemaId);
//busca com ordem
    //prefeitura primeiro+depois mais recente
    @Query("""
             SELECT c FROM Comentario c
             WHERE c.problema.id = :problemaId
             ORDER BY
             CASE
             WHEN c.usuario.tipo=com.mmm.model.TipoUsuario.PREFEITURA THEN 0
             ELSE 1
             END,
             c.dataCriacao DESC
             """)
List<Comentario>findByProblemaOrdenado(@Param("problemaId")Long problemaId);

    // conta quantos comentários tem um problema
    long countByProblemaId (Long problemaId);
// verifica se já existe um comentario oficial da prefeitura
    // será usado para impedir mais de um comentário oficial
    boolean existsByProblemaId_AndOficialTrue(Long problemaId);

    // metodo para dashboad
    //conta quantos comentários oficiais existem
    // em problemas de uma cidade,

    //será utilizado para calcular a participação da prefeitura
    // ex cidade Diamantina // retornar quantos comentários oficiais existem nos problemas dessa cidade
    long countByProblemaCidadeAndOficialTrue(String cidade);


    //conta quantos comentários oficiais existem em problemas
    // de uma cidade apartir de data/hora
    //vamos usar para saver se houve comentário oficial hoje
    //se for maior que 0 , seta para cima
    //se for 0 seta para baixo

    long countByProblemaCidadeAndOficialTrueAndDataCriacaoAfter(String cidade, LocalDateTime data);

    //lista somente os comentários oficiais
    // de uma cidade em ordem de mais recente para mais antigo

    List<Comentario>findByProblemaCidadeAndOficialTrueOrderByDataCriacaoDesc(String cidade);

}
