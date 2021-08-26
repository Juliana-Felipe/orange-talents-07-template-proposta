package br.com.zupacademy.proposta.proposta.cartao.bloqueio;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.client.consultaCartao.BloqueioResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Cartao cartao;
    private String userAgent;
    private String ipUsuário;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(BloqueioResponse bloqueioResponse, Cartao cartao) {
        this.idExterno = bloqueioResponse.getId();
        this.bloqueadoEm = bloqueioResponse.getBloqueadoEm();
        this.sistemaResponsavel = bloqueioResponse.getSistemaResponsavel();
        this.ativo = bloqueioResponse.isAtivo();
        this.cartao = cartao;
    }

    public Bloqueio(BloqueioResponse bloqueioResponse) {
        this.idExterno = bloqueioResponse.getId();
        this.bloqueadoEm = bloqueioResponse.getBloqueadoEm();
        this.sistemaResponsavel = bloqueioResponse.getSistemaResponsavel();
        this.ativo = bloqueioResponse.isAtivo();
    }

    public Bloqueio(@NotNull Cartao cartao, @NotBlank String userAgent, @NotBlank String ipUsuário) {
        this.bloqueadoEm = LocalDateTime.now();
        this.ativo = true;
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ipUsuário = ipUsuário;
    }
}
