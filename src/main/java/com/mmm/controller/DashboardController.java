package com.mmm.controller;

import com.mmm.dto.DashboardResponse;
import com.mmm.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // exemplo:
    // GET http://localhost:8080/dashboard?cidade=Diamantina&bairro=Lagoinha
    @GetMapping
    public DashboardResponse buscarResumo(
            @RequestParam String cidade,
            @RequestParam String bairro
    ) {
        return dashboardService.buscarResumo(cidade, bairro);
    }

    // ver no dashboard o resumo por cidade
    @GetMapping("/cidade")
    public DashboardResponse buscarResumoCidade(
            @RequestParam String cidade
    ) {
        return dashboardService.buscarResumoCidade(cidade);
    }

}
