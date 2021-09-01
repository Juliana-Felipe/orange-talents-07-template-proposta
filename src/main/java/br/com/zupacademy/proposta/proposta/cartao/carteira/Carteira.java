package br.com.zupacademy.proposta.proposta.cartao.carteira;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
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
    @Enumerated(EnumType.STRING)
    private TipoCarteira emissor;
    @Enumerated(EnumType.STRING)
    private EstadoDaAssociacao estadoDaAssociacao;
    @ManyToOne
    @JoinColumn
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(CarteiraResponse carteiraResponse, Cartao cartao) {
        this.idExterno = carteiraResponse.getId();
        this.email = carteiraResponse.getEmail();
        this.associadaEm = carteiraResponse.getAssociadaEm();
        this.emissor = TipoCarteira.valueOf(carteiraResponse.getEmissor());
        this.cartao = cartao;
    }

    public Carteira(String email, TipoCarteira tipoCarteira, Cartao cartao) {
        this.email = email;
        this.emissor = tipoCarteira;
        this.estadoDaAssociacao = EstadoDaAssociacao.EM_ESPERA;
        this.cartao = cartao;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getEmissor() {
        return emissor;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void alterarDadosDaAssociacao(EstadoDaAssociacao estadoDaAssociacao, String idExterno ) {
        this.estadoDaAssociacao = estadoDaAssociacao;
        this.idExterno = idExterno;
        this.associadaEm = LocalDateTime.now();
    }


}
