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

    // =========================
    // DASHBOARD POR CIDADE + BAIRRO (AGORA COM UF)
    // =========================
    public DashboardResponse buscarResumo(String uf, String cidade, String bairro) {

        // começo do dia de hoje
        LocalDateTime inicioDoDia = LocalDate.now().atStartOfDay();

        // pega todos os problemas da cidade dentro da UF
        List<Problema> problemasDaCidade =
                problemaRepository.findByUfAndCidadeOrderByDataCriacaoDesc(uf, cidade);

        // filtra os problemas do bairro escolhido
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

// seta: só sobe se teve problema hoje, senão fica vazio
        String setaProblemas = problemasHoje > 0 ? "↑" : "";

        // =========================
        // PARTICIPAÇÃO DA PREFEITURA
        // =========================

        long problemasComRespostaPrefeitura = 0;
        long respostaPrefeituraHoje = 0;

        for (Problema problema : problemasFiltrados) {

            List<Comentario> comentarios =
                    comentarioRepository.findByProblemaId(problema.getId());

            // se existir pelo menos 1 comentário oficial
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
        String setaPrefeitura = respostaPrefeituraHoje > 0 ? "↑" : "↓";

        return new DashboardResponse(
                uf, // 🔥 NOVO
                cidade,
                bairro,
                totalProblemas,
                setaProblemas,
                percentualRespostaPrefeitura,
                setaPrefeitura
        );
    }

    // =========================
    // DASHBOARD SOMENTE POR CIDADE (AGORA COM UF)
    // =========================
    public DashboardResponse buscarResumoCidade(String uf, String cidade) {

        LocalDateTime inicioDoDia = LocalDate.now().atStartOfDay();

        // pega todos os problemas da cidade dentro da UF
        List<Problema> problemas =
                problemaRepository.findByUfAndCidadeOrderByDataCriacaoDesc(uf, cidade);

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
                uf, // 🔥 NOVO
                cidade,
                "TODOS",
                totalProblemas,
                setaProblemas,
                percentual,
                setaPrefeitura
        );
    }
}
