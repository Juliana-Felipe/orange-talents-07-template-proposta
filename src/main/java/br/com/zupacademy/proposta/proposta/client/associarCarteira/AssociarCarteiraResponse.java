package br.com.zupacademy.proposta.proposta.client.associarCarteira;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AssociarCarteiraResponse {
    private String resultado;
    private String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AssociarCarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
