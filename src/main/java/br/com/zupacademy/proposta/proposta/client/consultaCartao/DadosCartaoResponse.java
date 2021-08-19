package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.time.LocalDateTime;
import java.util.List;

public class DadosCartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueio> bloqueios;
    private List<Aviso> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    private int limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    @Deprecated
    public DadosCartaoResponse() {
    }

    public DadosCartaoResponse(String id, LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas, int limite, Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
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
}
