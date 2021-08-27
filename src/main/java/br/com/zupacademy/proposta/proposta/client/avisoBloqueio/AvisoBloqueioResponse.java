package br.com.zupacademy.proposta.proposta.client.avisoBloqueio;

import com.fasterxml.jackson.annotation.JsonCreator;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class AvisoBloqueioResponse {
    private ResultadoAvisoBloqueio resultado;

    @JsonCreator(mode=PROPERTIES)
    public AvisoBloqueioResponse(ResultadoAvisoBloqueio resultado) {
        this.resultado = resultado;
    }

    public ResultadoAvisoBloqueio getResultado() {
        return resultado;
    }
}
