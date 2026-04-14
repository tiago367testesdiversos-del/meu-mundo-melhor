package com.mmm.dto;

// DTO = objeto usado para devolver os dados prontos para o front
public class DashboardResponse {

    private String uf;
    private String cidade;
    private String bairro;

    // quantidade total de problemas naquela cidade/bairro
    private long totalProblemas;

    // seta do card de problemas: "↑" ou "↓"
    private String setaProblemas;

    // percentual de participação/resposta da prefeitura
    private double percentualRespostaPrefeitura;

    // seta do card da prefeitura: "↑" ou "↓"
    private String setaPrefeitura;

    public DashboardResponse() {
    }

    public DashboardResponse(String uf,
                             String cidade,
                             String bairro,
                             long totalProblemas,
                             String setaProblemas,
                             double percentualRespostaPrefeitura,
                             String setaPrefeitura) {
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.totalProblemas = totalProblemas;
        this.setaProblemas = setaProblemas;
        this.percentualRespostaPrefeitura = percentualRespostaPrefeitura;
        this.setaPrefeitura = setaPrefeitura;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public long getTotalProblemas() {
        return totalProblemas;
    }

    public void setTotalProblemas(long totalProblemas) {
        this.totalProblemas = totalProblemas;
    }

    public String getSetaProblemas() {
        return setaProblemas;
    }

    public void setSetaProblemas(String setaProblemas) {
        this.setaProblemas = setaProblemas;
    }

    public double getPercentualRespostaPrefeitura() {
        return percentualRespostaPrefeitura;
    }

    public void setPercentualRespostaPrefeitura(double percentualRespostaPrefeitura) {
        this.percentualRespostaPrefeitura = percentualRespostaPrefeitura;
    }

    public String getSetaPrefeitura() {
        return setaPrefeitura;
    }

    public void setSetaPrefeitura(String setaPrefeitura) {
        this.setaPrefeitura = setaPrefeitura;
    }
}
