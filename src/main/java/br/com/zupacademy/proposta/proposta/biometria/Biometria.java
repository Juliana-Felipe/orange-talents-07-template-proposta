package br.com.zupacademy.proposta.proposta.biometria;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String fingerPrint;

    @NotNull
    private LocalDateTime criadaEm;

    @ManyToOne
    @NotNull
    private Cartao cartao;

    public Biometria(String fingerPrint, Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
        this.criadaEm = LocalDateTime.now();
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
