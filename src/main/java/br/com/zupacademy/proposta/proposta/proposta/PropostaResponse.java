package br.com.zupacademy.proposta.proposta.proposta;

public class PropostaResponse {
    private long id;
    private String nome;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
    }

}
