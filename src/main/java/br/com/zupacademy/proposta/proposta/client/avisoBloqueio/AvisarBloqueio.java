package br.com.zupacademy.proposta.proposta.client.avisoBloqueio;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRepository;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.AvaliacaoFinanceiraClient;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.AvaliacaoFinanceiraRequest;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.AvaliacaoFinanceiraResponse;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.ResultadoSolicitacao;
import br.com.zupacademy.proposta.proposta.proposta.Proposta;
import br.com.zupacademy.proposta.proposta.proposta.PropostaRepository;
import br.com.zupacademy.proposta.proposta.proposta.ResultadoAvalicacao;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvisarBloqueio {
    private AvisoBloqueioClient avisoBloqueioClient;
    private CartaoRepository cartaoRepository;

    public AvisarBloqueio(AvisoBloqueioClient avisoBloqueioClient, CartaoRepository cartaoRepository) {
        this.avisoBloqueioClient = avisoBloqueioClient;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 30000)
    public void aviso() {
        AvisoBloqueioResponse response = null;
        List<Cartao> solicitacoesDeBloqueio = cartaoRepository.findByEstadoDoCartao(EstadoDoCartao.BLOQUEIO_SOLICITADO);
        for (Cartao cartao : solicitacoesDeBloqueio) {
            AvisoBloqueioRequest avisoBloqueioRequest = new AvisoBloqueioRequest("Api Propostas - modo Scheduled");
            try {
                response = avisoBloqueioClient.bloqueia(cartao.getNumeroCartao(), avisoBloqueioRequest);
                cartao.alterarEstadoDoCartao(EstadoDoCartao.BLOQUEADO);

            } catch (FeignException e) {
                if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                    System.out.println("O cartão de id: " + cartao.getId() + " apresenta falha para bloqueio na api legada de cartões");
                } else System.out.println(e);

            }
            cartaoRepository.save(cartao);
        }
    }


    public void avisoImediato(Cartao cartao) {
        AvisoBloqueioResponse response = null;
        AvisoBloqueioRequest avisoBloqueioRequest = new AvisoBloqueioRequest("Api Propostas - imediato");
        try {
            response = avisoBloqueioClient.bloqueia(cartao.getNumeroCartao(), avisoBloqueioRequest);
            cartao.alterarEstadoDoCartao(EstadoDoCartao.BLOQUEADO);

        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                System.out.println("O cartão de id: " + cartao.getId() + " apresenta falha para bloqueio na api legada de cartões");
            } else System.out.println(e);

        }
        cartaoRepository.save(cartao);
    }



}


