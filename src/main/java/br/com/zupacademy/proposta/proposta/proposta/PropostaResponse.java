package br.com.zupacademy.proposta.proposta.proposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private long id;
    private String documento;
    private String email;
    private String nome;
    private Endereco endereco;
    private BigDecimal salario;
    private String resultadoAvalicacao;
    private String numeroCartao;


    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.resultadoAvalicacao = proposta.getResultadoAvalicacao().toString();
        this.numeroCartao = proposta.getCartao().getNumeroCartao();
    }

    public long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getResultadoAvalicacao() {
        return resultadoAvalicacao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
