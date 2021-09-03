package br.com.zupacademy.proposta.proposta.proposta;

import br.com.zupacademy.proposta.proposta.criptografia.Criptografia;

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
        Criptografia criptografia = new Criptografia();
        this.id = proposta.getId();
        this.documento = criptografia.descriptografar(proposta.getDocumento());
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.resultadoAvalicacao = proposta.getResultadoAvalicacao().toString();
        this.numeroCartao = null;
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

    public void setNumeroCartao(Proposta proposta) {
        this.numeroCartao = proposta.getCartao().getNumeroCartao();
    }
}
