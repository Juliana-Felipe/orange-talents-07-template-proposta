package br.com.zupacademy.proposta.proposta.cartao;

import br.com.zupacademy.proposta.proposta.biometria.Biometria;
import br.com.zupacademy.proposta.proposta.cartao.aviso.Aviso;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.carteira.Carteira;
import br.com.zupacademy.proposta.proposta.client.consultaCartao.DadosCartaoResponse;
import br.com.zupacademy.proposta.proposta.proposta.Proposta;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import static javax.persistence.CascadeType.*;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String numeroCartao;
    private LocalDateTime emitidoEm;
    private String titular;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Bloqueio> bloqueios;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Aviso> avisos;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Carteira> carteiras;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias;
    @OneToOne(mappedBy = "cartao", cascade = {MERGE, PERSIST, REMOVE})
    private Vencimento vencimento;
    @OneToOne(mappedBy = "cartao")
    @NotNull
    private Proposta proposta;
    private String proposta_id;
    @Enumerated(EnumType.STRING)
    private EstadoDoCartao estadoDoCartao;
    @Enumerated(EnumType.STRING)
    private NotificacaoDeAviso notificacaoDeAviso;

    public Cartao(DadosCartaoResponse response, Proposta proposta) {
        this.numeroCartao = response.getId();
        this.emitidoEm = response.getEmitidoEm();
        this.titular = response.getTitular();
        this.proposta = proposta;
        this.proposta_id = response.getIdProposta();
        this.estadoDoCartao = EstadoDoCartao.LIBERADO;
        this.notificacaoDeAviso = NotificacaoDeAviso.USO_PADRAO;
    }

    public Cartao() {
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Long getId() {
        return id;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public void alterarEstadoDoCartao(EstadoDoCartao estadoDoCartao) {
        this.estadoDoCartao = estadoDoCartao;
    }

    public void alterarNotificacaoDeAviso(NotificacaoDeAviso notificacaoDeAviso){this.notificacaoDeAviso = notificacaoDeAviso; }
}
