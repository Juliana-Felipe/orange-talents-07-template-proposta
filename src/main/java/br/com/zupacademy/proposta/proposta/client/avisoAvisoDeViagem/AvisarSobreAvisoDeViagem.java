package br.com.zupacademy.proposta.proposta.client.avisoAvisoDeViagem;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import br.com.zupacademy.proposta.proposta.cartao.NotificacaoDeAviso;
import br.com.zupacademy.proposta.proposta.cartao.aviso.Aviso;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisoBloqueioClient;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisoBloqueioRequest;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisoBloqueioResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvisarSobreAvisoDeViagem {
    private AvisoDeViagemClient avisoDeViagemClient;
    private CartaoRepository cartaoRepository;

    public AvisarSobreAvisoDeViagem(AvisoDeViagemClient avisoDeViagemClient, CartaoRepository cartaoRepository) {
        this.avisoDeViagemClient = avisoDeViagemClient;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 30000)
    public void aviso() {
        AvisoDeViagemResponse response = null;
        List<Cartao> solicitacoesDeAviso = cartaoRepository.findByNotificacaoDeAviso(NotificacaoDeAviso.AVISO_SOLICITADO);
        for (Cartao cartao : solicitacoesDeAviso) {
            List<Aviso> avisos = cartao.getAvisos();
            for (Aviso aviso : avisos) {
                AvisoDeViagemRequest avisoDeViagemRequest = new AvisoDeViagemRequest(aviso.getDestino(), aviso.getValidoAte());
                try {
                    response = avisoDeViagemClient.notifica(cartao.getNumeroCartao(), avisoDeViagemRequest);
                    cartao.alterarNotificacaoDeAviso(NotificacaoDeAviso.AVISO_GERADO);

                } catch (FeignException e) {
                    System.out.println(e);
                }
                cartaoRepository.save(cartao);
            }
        }
    }


    public void avisoImediato(Aviso aviso) {
        AvisoDeViagemResponse response = null;
        Cartao cartao = aviso.getCartao();
        AvisoDeViagemRequest avisoDeViagemRequest = new AvisoDeViagemRequest(aviso.getDestino(), aviso.getValidoAte());
        try {
            response = avisoDeViagemClient.notifica(cartao.getNumeroCartao(), avisoDeViagemRequest);
            cartao.alterarNotificacaoDeAviso(NotificacaoDeAviso.AVISO_GERADO);

        } catch (FeignException e) {
            System.out.println(e);
        }
        cartaoRepository.save(cartao);
    }
}





