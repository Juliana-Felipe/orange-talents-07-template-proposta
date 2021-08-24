package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.time.LocalDateTime;
import java.util.List;

public class DadosCartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioResponse> bloqueios;
    private List<AvisoResponse> avisos;
    private List<CarteiraResponse> carteiras;
    private List<ParcelaResponse> parcelas;
    private int limite;
    private RenegociacaoResponse renegociacao;
    private VencimentoResponse vencimento;
    private String idProposta;

    @Deprecated
    public DadosCartaoResponse() {
    }

    public DadosCartaoResponse(String id, LocalDateTime emitidoEm, String titular, List<BloqueioResponse> bloqueios, List<AvisoResponse> avisos, List<CarteiraResponse> carteiras, List<ParcelaResponse> parcelas, int limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueioResponse> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoResponse> getAvisos() {
        return avisos;
    }

    public List<CarteiraResponse> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaResponse> getParcelas() {
        return parcelas;
    }

    public int getLimite() {
        return limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
