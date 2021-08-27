package br.com.zupacademy.proposta.proposta.client.avisoBloqueio;

public class AvisoBloqueioRequest {
    private String sistemaResponsavel;

    public AvisoBloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
