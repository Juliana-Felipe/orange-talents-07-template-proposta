package br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira;

import br.com.zupacademy.proposta.proposta.proposta.Proposta;
import br.com.zupacademy.proposta.proposta.proposta.PropostaRepository;
import br.com.zupacademy.proposta.proposta.proposta.ResultadoAvalicacao;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class GerarAvaliacao {
    private AvaliacaoFinanceiraClient avaliacaoFinanceiraClient;
    private PropostaRepository propostaRepository;

    public GerarAvaliacao(AvaliacaoFinanceiraClient avaliacaoFinanceiraClient, PropostaRepository propostaRepository) {
        this.avaliacaoFinanceiraClient = avaliacaoFinanceiraClient;
        this.propostaRepository = propostaRepository;
    }

    public void avaliacaoFinanceira(Proposta proposta) {
        AvaliacaoFinanceiraResponse response = null;
        AvaliacaoFinanceiraRequest avaliacaoFinanceiraRequest = new AvaliacaoFinanceiraRequest(proposta.getDocumento(),
                proposta.getNome(),
                proposta.getId().toString());
        try {
            response = avaliacaoFinanceiraClient.avalia(avaliacaoFinanceiraRequest);
            proposta.atualizaResultadoAvaliacao(response.getResultadoSolicitacao());

        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                proposta.atualizaResultadoAvaliacao(ResultadoSolicitacao.COM_RESTRICAO);
            } else proposta.atualizaResultadoAvaliacao(ResultadoSolicitacao.SEM_RESPOSTA);
        }

        propostaRepository.save(proposta);
    }

    @Scheduled(fixedDelay = 30000)
    public void atribuirAvaliacao() {
        AvaliacaoFinanceiraResponse response = null;
        List<Proposta> listaEmEspera = propostaRepository.findByResultadoAvalicacao(ResultadoAvalicacao.EM_ESPERA);
        for (Proposta proposta : listaEmEspera) {
            AvaliacaoFinanceiraRequest avaliacaoFinanceiraRequest = new AvaliacaoFinanceiraRequest(proposta.getDocumento(),
                    proposta.getNome(),
                    proposta.getId().toString());

            try {
                response = avaliacaoFinanceiraClient.avalia(avaliacaoFinanceiraRequest);
                proposta.atualizaResultadoAvaliacao(response.getResultadoSolicitacao());
            } catch (FeignException e) {
                if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                    proposta.atualizaResultadoAvaliacao(ResultadoSolicitacao.COM_RESTRICAO);
                } else proposta.atualizaResultadoAvaliacao(ResultadoSolicitacao.SEM_RESPOSTA);
            }

            propostaRepository.save(proposta);
        }
    }

}
