package com.mmm.repository;
import com.mmm.model.Problema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemaRepository extends JpaRepository<Problema,Long>  {
//evitar duplicidade de problemas
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
}


//