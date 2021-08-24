package br.com.zupacademy.proposta.proposta.cartao;

import br.com.zupacademy.proposta.proposta.client.consultaCartao.VencimentoResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vencimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String idExterno;
    private int dia;
    private LocalDateTime dataDeCriacao;

    @OneToOne
    private Cartao cartao;

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(VencimentoResponse vencimentoResponse) {
        this.idExterno = vencimentoResponse.getId();
        this.dia = vencimentoResponse.getDia();
        this.dataDeCriacao = vencimentoResponse.getDataDeCriacao();
    }

}
