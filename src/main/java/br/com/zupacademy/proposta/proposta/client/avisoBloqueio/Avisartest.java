package br.com.zupacademy.proposta.proposta.client.avisoBloqueio;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Avisartest {

    private BloqueioRepository bloqueioRepository;
    private CartaoRepository cartaoRepository;

    public Avisartest(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository) {
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 30000)
    public void aviso() {
        AvisoBloqueioResponse response = null;
        Bloqueio bloqueio = bloqueioRepository.findBySistemaResponsavel("Api Propostas");
        Cartao cartao = bloqueio.getCartao();
            try {
             cartao.alterarEstadoDoCartao(EstadoDoCartao.BLOQUEADO);
            } catch (Exception e) {
               System.out.println(e);

            }
            cartaoRepository.save(cartao);
        }
    }

