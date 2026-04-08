package com.mmm.service;

import com.mmm.dto.DashboardResponse;
import com.mmm.model.Comentario;
import com.mmm.model.Problema;
import com.mmm.repository.ComentarioRepository;
import com.mmm.repository.ProblemaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    private final ProblemaRepository problemaRepository;
    private final ComentarioRepository comentarioRepository;

    public DashboardService(ProblemaRepository problemaRepository,
                            ComentarioRepository comentarioRepository) {
        this.problemaRepository = problemaRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public DashboardResponse buscarResumo(String cidade, String bairro) {

        // começo do dia de hoje
        LocalDateTime inicioDoDia = LocalDate.now().atStartOfDay();

        // pega todos os problemas da cidade
        List<Problema> problemasDaCidade =
                problemaRepository.findByCidadeOrderByDataCriacaoDesc(cidade);

        // filtra os problemas do bairro escolhido
        List<Problema> problemasFiltrados = problemasDaCidade.stream()
                .filter(p -> p.getBairro() != null && p.getBairro().equalsIgnoreCase(bairro))
                .toList();

        // total de problemas da cidade/bairro
        long totalProblemas = problemasFiltrados.size();

        // quantos problemas desse bairro foram criados hoje
        long problemasHoje = problemasFiltrados.stream()
                .filter(p -> p.getDataCriacao() != null
                        && !p.getDataCriacao().isBefore(inicioDoDia))
                .count();

        // se teve problema hoje -> seta para cima
        // se não teve -> seta para baixo
        String setaProblemas = problemasHoje > 0 ? "↑" : "↓";

        // calcular participação da prefeitura
        // percentual = quantidade de problemas do bairro que receberam comentário oficial
        // dividido pelo total de problemas do bairro
        long problemasComRespostaPrefeitura = 0;
        long respostaPrefeituraHoje = 0;

        for (Problema problema : problemasFiltrados) {
            List<Comentario> comentarios = comentarioRepository.findByProblemaId(problema.getId());

            // se existir pelo menos 1 comentário oficial, conta como problema respondido
            boolean temComentarioOficial = comentarios.stream()
                    .anyMatch(Comentario::isOficial);

            if (temComentarioOficial) {
                problemasComRespostaPrefeitura++;
            }

            // verifica se teve comentário oficial hoje
            boolean teveRespostaHoje = comentarios.stream()
                    .anyMatch(c -> c.isOficial()
                            && c.getDataCriacao() != null
                            && !c.getDataCriacao().isBefore(inicioDoDia));

            if (teveRespostaHoje) {
                respostaPrefeituraHoje++;
            }
        }

        double percentualRespostaPrefeitura = 0.0;

        // evita divisão por zero
        if (totalProblemas > 0) {
            percentualRespostaPrefeitura =
                    (problemasComRespostaPrefeitura * 100.0) / totalProblemas;
        }

        // arredonda para 2 casas decimais
        percentualRespostaPrefeitura =
                Math.round(percentualRespostaPrefeitura * 100.0) / 100.0;

        // se teve comentário oficial hoje -> seta para cima
        // se não teve -> seta para baixo
        String setaPrefeitura = respostaPrefeituraHoje > 0 ? "↑" : "↓";

        return new DashboardResponse(
                cidade,
                bairro,
                totalProblemas,
                setaProblemas,
                percentualRespostaPrefeitura,
                setaPrefeitura
        );
    }
    public DashboardResponse buscarResumoCidade(String cidade) {

        LocalDateTime inicioDoDia = LocalDate.now().atStartOfDay();

        // pega todos os problemas da cidade
        List<Problema> problemas = problemaRepository
                .findByCidadeOrderByDataCriacaoDesc(cidade);

        long totalProblemas = problemas.size();

        long problemasHoje = problemas.stream()
                .filter(p -> p.getDataCriacao() != null
                        && !p.getDataCriacao().isBefore(inicioDoDia))
                .count();

        String setaProblemas = problemasHoje > 0 ? "↑" : "↓";

        long problemasComRespostaPrefeitura = 0;
        long respostaPrefeituraHoje = 0;

        for (Problema problema : problemas) {

            List<Comentario> comentarios =
                    comentarioRepository.findByProblemaId(problema.getId());

            boolean temComentarioOficial = comentarios.stream()
                    .anyMatch(Comentario::isOficial);

            if (temComentarioOficial) {
                problemasComRespostaPrefeitura++;
            }

            boolean teveRespostaHoje = comentarios.stream()
                    .anyMatch(c -> c.isOficial()
                            && c.getDataCriacao() != null
                            && !c.getDataCriacao().isBefore(inicioDoDia));

            if (teveRespostaHoje) {
                respostaPrefeituraHoje++;
            }
        }

        double percentual = 0.0;

        if (totalProblemas > 0) {
            percentual = (problemasComRespostaPrefeitura * 100.0) / totalProblemas;
        }

        percentual = Math.round(percentual * 100.0) / 100.0;

        String setaPrefeitura = respostaPrefeituraHoje > 0 ? "↑" : "↓";

        return new DashboardResponse(
                cidade,
                "TODOS", // aqui não tem bairro
                totalProblemas,
                setaProblemas,
                percentual,
                setaPrefeitura
        );
    }


}
