package com.mmm.repository;

import com.mmm.model.Problema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProblemaRepository extends JpaRepository<Problema, Long> {

    // evita duplicidade:
    // mesmo título + mesma UF + mesma cidade + mesmo bairro
    boolean existsByTituloAndUfAndCidadeAndBairro(
            String titulo,
            String uf,
            String cidade,
            String bairro
    );

    // lista tudo do mais recente para o mais antigo
    List<Problema> findAllByOrderByDataCriacaoDesc();

    // feed paginado, mais recente primeiro
    Page<Problema> findAllByOrderByDataCriacaoDesc(Pageable pageable);

    // conta total de problemas de uma cidade dentro de uma UF
    long countByUfAndCidade(String uf, String cidade);

    // conta quantos problemas de uma cidade/UF foram criados depois de uma data
    long countByUfAndCidadeAndDataCriacaoAfter(String uf, String cidade, LocalDateTime data);

    // lista todos os problemas de uma cidade/UF por data mais recente
    List<Problema> findByUfAndCidadeOrderByDataCriacaoDesc(String uf, String cidade);
}
