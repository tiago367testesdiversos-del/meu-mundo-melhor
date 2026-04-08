package com.mmm.repository;
import com.mmm.model.Problema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProblemaRepository extends JpaRepository<Problema,Long>  {
//evitar duplicidade de problemas
    //ex: mesmo titulo, mesma cidade+bairro
    boolean existsByTituloAndCidadeAndBairro(
            String titulo,
            String cidade,
            String bairro
    );

    List<Problema> findAllByOrderByDataCriacaoDesc();

    // Feed de produção
    // paginação (evita trazer tudo de uma vez)
    // ordenação já embutida ( mais recente)

    Page<Problema> findAllByOrderByDataCriacaoDesc(Pageable pageable);

    // contra o total de problema de uma cidade
    long countByCidade(String cidade);

    //conta quantos problemas de uma cidade foram criados depois de uma data
    //vamos usar para saber quantos problema forma criados hoje
    long countByCidadeAndDataCriacaoAfter(String cidade, LocalDateTime data);

//listaq todos os problemas de uma cidade por data mais recente

    List<Problema> findByCidadeOrderByDataCriacaoDesc ( String cidade);
}


//