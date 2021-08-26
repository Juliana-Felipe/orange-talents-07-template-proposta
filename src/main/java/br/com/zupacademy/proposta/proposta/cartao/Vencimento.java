package br.com.zupacademy.proposta.proposta.cartao;

import br.com.zupacademy.proposta.proposta.client.consultaCartao.VencimentoResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.*;

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

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(VencimentoResponse vencimentoResponse, Cartao cartao) {
        this.idExterno = vencimentoResponse.getId();
        this.dia = vencimentoResponse.getDia();
        this.dataDeCriacao = vencimentoResponse.getDataDeCriacao();
        this.cartao = cartao;
    }

}
