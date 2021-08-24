package br.com.zupacademy.proposta.proposta.biometria;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String fingerPrint;

    @ManyToOne
    @NotNull
    private Cartao cartao;

    public Biometria(String fingerPrint, Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Biometria() {
    }

    public long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
