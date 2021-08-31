package br.com.zupacademy.proposta.proposta.cartao.aviso;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.client.consultaCartao.AvisoResponse;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date validoAte;
    private String destino;
    private LocalDateTime momentoDoAviso;
    @ManyToOne
    private Cartao cartao;
    private String userAgent;
    private String ipUsuário;

    @Deprecated
    public Aviso() {
    }

    public Aviso(AvisoResponse avisoResponse, Cartao cartao) {
        this.validoAte = avisoResponse.getValidoAte();
        this.destino = avisoResponse.getDestino();
        this.cartao = cartao;
        this.momentoDoAviso = LocalDateTime.now();
     }

    public Aviso(@Future Date validoAte, @NotBlank String destino, @NotNull Cartao cartao, String userAgent, String ipUsuário) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.momentoDoAviso = LocalDateTime.now();
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ipUsuário = ipUsuário;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Date getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Object getId() {
        return id;
    }
}
