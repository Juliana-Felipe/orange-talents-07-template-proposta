package br.com.zupacademy.proposta.proposta.cartao;

import br.com.zupacademy.proposta.proposta.client.consultaCartao.CarteiraResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String idExterno;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(CarteiraResponse carteiraResponse, Cartao cartao) {
        this.idExterno = carteiraResponse.getId();
        this.email = carteiraResponse.getEmail();
        this.associadaEm = carteiraResponse.getAssociadaEm();
        this.emissor = carteiraResponse.getEmissor();
        this.cartao = cartao;
    }
}
