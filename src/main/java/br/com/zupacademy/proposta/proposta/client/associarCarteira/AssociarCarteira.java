package br.com.zupacademy.proposta.proposta.client.associarCarteira;

import br.com.zupacademy.proposta.proposta.cartao.carteira.Carteira;
import br.com.zupacademy.proposta.proposta.cartao.carteira.CarteiraRepository;
import br.com.zupacademy.proposta.proposta.cartao.carteira.EstadoDaAssociacao;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssociarCarteira {
    private AssociarCarteiraClient associarCarteiraClient;
    private CarteiraRepository carteiraRepository;

    public AssociarCarteira(AssociarCarteiraClient associarCarteiraClient, CarteiraRepository carteiraRepository) {
        this.associarCarteiraClient = associarCarteiraClient;
        this.carteiraRepository = carteiraRepository;
    }

    @Scheduled(fixedDelay = 30000)
    public void aviso() {
        AssociarCarteiraResponse response = null;
        List<Carteira> solicitacoesDeAssociacao = carteiraRepository.findByEstadoDaAssociacao(EstadoDaAssociacao.EM_ESPERA);
        for (Carteira carteira : solicitacoesDeAssociacao) {
            AssociarCarteiraRequest request = new AssociarCarteiraRequest(carteira.getEmail(), carteira.getEmissor());
            try {
                response = associarCarteiraClient.associa(carteira.getCartao().getNumeroCartao(), request);
                carteira.alterarDadosDaAssociacao(EstadoDaAssociacao.ASSOCIADA, response.getId());

            } catch (FeignException e) {
                System.out.println(e);

            }
            carteiraRepository.save(carteira);
        }
    }


    public void associacaoImediata(Carteira carteira) {
        AssociarCarteiraResponse response = null;
        AssociarCarteiraRequest request = new AssociarCarteiraRequest(carteira.getEmail(), carteira.getEmissor());
        try {
            response = associarCarteiraClient.associa(carteira.getCartao().getNumeroCartao(), request);
            carteira.alterarDadosDaAssociacao(EstadoDaAssociacao.ASSOCIADA, response.getId());
        } catch (FeignException e) {
            System.out.println(e);

        }
        carteiraRepository.save(carteira);
    }
}
