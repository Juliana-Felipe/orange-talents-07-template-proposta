package br.com.zupacademy.proposta.proposta.proposta;

import br.com.zupacademy.proposta.proposta.client.ResultadoSolicitacao;
import br.com.zupacademy.proposta.proposta.proposta.config.CPFOrCNPJ;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CPFOrCNPJ
    @NotBlank
    @Column(unique = true)
    private String documento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @Embedded
    @Valid
    private Endereco endereco;
    @NotNull
    @Positive
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private ResultadoAvalicação resultadoAvalicação;

    public Proposta(String documento, String email, String nome, Endereco endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Deprecated
    public Proposta() {
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void atualizaResultadoAvaliacao(ResultadoSolicitacao resultadoSolicitacao) {
        this.resultadoAvalicação = resultadoSolicitacao.getResultado();
    }
}

