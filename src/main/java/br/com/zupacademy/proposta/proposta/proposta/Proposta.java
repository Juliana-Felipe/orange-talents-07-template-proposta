package br.com.zupacademy.proposta.proposta.proposta;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.ResultadoSolicitacao;
import br.com.zupacademy.proposta.proposta.criptografia.Criptografia;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(unique = true)
    private String documento;
    @Email
    @NotBlank
    @Column(unique = true)
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
    private ResultadoAvalicacao resultadoAvalicacao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Cartao cartao;


     public Proposta(String documento, String email, String nome, Endereco endereco, BigDecimal salario) {
         Criptografia criptografia = new Criptografia();
        this.documento = criptografia.criptografar(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.resultadoAvalicacao = ResultadoAvalicacao.EM_ESPERA;
    }

    @Deprecated
    public Proposta() {
    }

    public void atualizaResultadoAvaliacao(ResultadoSolicitacao resultadoSolicitacao) {
        this.resultadoAvalicacao = resultadoSolicitacao.getResultado();
    }
    public void atualizaCartao(Cartao cartao) {
        this.cartao = cartao;
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

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public ResultadoAvalicacao getResultadoAvalicacao() {
        return resultadoAvalicacao;
    }

    public Cartao getCartao() { return cartao; }
}

