package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import br.com.zupacademy.proposta.proposta.cartao.*;
import br.com.zupacademy.proposta.proposta.cartao.aviso.Aviso;
import br.com.zupacademy.proposta.proposta.cartao.aviso.AvisoRepository;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRepository;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.ResultadoSolicitacao;
import br.com.zupacademy.proposta.proposta.proposta.Proposta;
import br.com.zupacademy.proposta.proposta.proposta.PropostaRepository;
import br.com.zupacademy.proposta.proposta.proposta.ResultadoAvalicacao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BucarNumeroDoCartao {

    private PropostaRepository propostaRepository;
    private ConsultaCartaoClient consultaCartaoClient;
    private AvisoRepository avisoRepository;
    private BloqueioRepository bloqueioRepository;
    private CarteiraRepository carteiraRepository;
    private VencimentoRepository vencimentoRepository;
    private CartaoRepository cartaoRepository;


    public BucarNumeroDoCartao(PropostaRepository propostaRepository, ConsultaCartaoClient consultaCartaoClient, AvisoRepository avisoRepository, BloqueioRepository bloqueioRepository, CarteiraRepository carteiraRepository, VencimentoRepository vencimentoRepository, CartaoRepository cartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.consultaCartaoClient = consultaCartaoClient;
        this.avisoRepository = avisoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.carteiraRepository = carteiraRepository;
        this.vencimentoRepository = vencimentoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 30000)
    private void atribuirCartao() {
        List<Proposta> elegiveis = propostaRepository.findByResultadoAvalicacao(ResultadoAvalicacao.ELEGIVEL);

        for (Proposta elegivel : elegiveis) {
            DadosCartaoResponse cartaoResponse = null;
            try {
                cartaoResponse = consultaCartaoClient.consultaCartao(elegivel.getId());
                Cartao cartao = new Cartao(cartaoResponse, elegivel);
                cartaoRepository.save(cartao);
                elegivel.atualizaCartao(cartao);
                elegivel.atualizaResultadoAvaliacao(ResultadoSolicitacao.CARTAO_ENCONTRADO);
                propostaRepository.save(elegivel);
                instanciandoEntitys(cartaoResponse, cartao, cartaoRepository);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void instanciandoEntitys(DadosCartaoResponse cartaoResponse, Cartao cartao, CartaoRepository cartaoRepository) {
        for (int i = 0; i < cartaoResponse.getAvisos().size(); i++) {
            Aviso aviso = new Aviso(cartaoResponse.getAvisos().get(i), cartao);
            cartao.alterarNotificacaoDeAviso(NotificacaoDeAviso.AVISO_GERADO);
            avisoRepository.save(aviso);
            cartaoRepository.save(cartao);
        }
        for (int i = 0; i < cartaoResponse.getBloqueios().size(); i++) {
            Bloqueio bloqueio = new Bloqueio(cartaoResponse.getBloqueios().get(i), cartao);
            cartao.alterarEstadoDoCartao(EstadoDoCartao.BLOQUEADO);
            bloqueioRepository.save(bloqueio);
            cartaoRepository.save(cartao);
        }
        for (int i = 0; i < cartaoResponse.getCarteiras().size(); i++) {
            Carteira carteira = new Carteira(cartaoResponse.getCarteiras().get(i), cartao);
            carteiraRepository.save(carteira);
        }
        Vencimento vencimento = new Vencimento(cartaoResponse.getVencimento(), cartao);
        vencimentoRepository.save(vencimento);
    }
}
