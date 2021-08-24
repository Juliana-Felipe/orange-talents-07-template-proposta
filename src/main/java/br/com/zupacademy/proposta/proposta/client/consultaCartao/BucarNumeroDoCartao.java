package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import br.com.zupacademy.proposta.proposta.cartao.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.ResultadoSolicitacao;
import br.com.zupacademy.proposta.proposta.proposta.Proposta;
import br.com.zupacademy.proposta.proposta.proposta.PropostaRepository;
import br.com.zupacademy.proposta.proposta.proposta.ResultadoAvalicacao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BucarNumeroDoCartao {

    PropostaRepository propostaRepository;
    ConsultaCartaoClient consultaCartaoClient;

    public BucarNumeroDoCartao(PropostaRepository propostaRepository, ConsultaCartaoClient consultaCartaoClient) {
        this.propostaRepository = propostaRepository;
        this.consultaCartaoClient = consultaCartaoClient;
    }

    @Scheduled(fixedDelay = 30000)
    private void atribuirCartao() {
        List<Proposta> elegiveis = propostaRepository.findByResultadoAvalicacao(ResultadoAvalicacao.ELEGIVEL);

        for (Proposta elegivel : elegiveis) {
            DadosCartaoResponse cartaoResponse = null;
            try {
                cartaoResponse = consultaCartaoClient.consultaCartao(elegivel.getId());
                elegivel.atualizaCartao(new Cartao(cartaoResponse, elegivel));
                elegivel.atualizaResultadoAvaliacao(ResultadoSolicitacao.CARTAO_ENCONTRADO);
                propostaRepository.save(elegivel);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
