package br.com.zupacademy.proposta.proposta.cartao;

import br.com.zupacademy.proposta.proposta.client.consultaCartao.AvisoResponse;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date validoAte;
    private String destino;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(AvisoResponse avisoResponse, Cartao cartao) {
        this.validoAte = avisoResponse.getValidoAte();
        this.destino = avisoResponse.getDestino();
        this.cartao = cartao;
     }

}
