package br.com.zupacademy.proposta.proposta.client;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvaliacaoFinanceiraResponse {
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotNull
    private ResultadoSolicitacao resultadoSolicitacao;
    @NotBlank
    private String idProposta;

    @JsonCreator

    public AvaliacaoFinanceiraResponse(String documento, String nome, ResultadoSolicitacao resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}