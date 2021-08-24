package br.com.zupacademy.proposta.proposta.cartao;

import br.com.zupacademy.proposta.proposta.client.consultaCartao.BloqueioResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String idExterno;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(BloqueioResponse bloqueioResponse) {
        this.idExterno = bloqueioResponse.getId();
        this.bloqueadoEm = bloqueioResponse.getBloqueadoEm();
        this.sistemaResponsavel = bloqueioResponse.getSistemaResponsavel();
        this.ativo = bloqueioResponse.isAtivo();
    }


}
