package br.com.zupacademy.proposta.proposta.proposta;

import br.com.zupacademy.proposta.proposta.client.AvaliacaoFinanceiraClient;
import br.com.zupacademy.proposta.proposta.client.AvaliacaoFinanceiraRequest;
import br.com.zupacademy.proposta.proposta.client.AvaliacaoFinanceiraResponse;
import br.com.zupacademy.proposta.proposta.client.ResultadoSolicitacao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;
    @Autowired
    AvaliacaoFinanceiraClient avaliacaoFinanceiraClient;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraResposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder) {
        if (propostaRepository.findByDocumento(request.limpa(request.getDocumento())).isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Proposta novaProposta = request.converte();
        propostaRepository.save(novaProposta);
        AvaliacaoFinanceiraResponse avaliacaoFinanceiraResponse = avaliacaoFinanceira(novaProposta);
        novaProposta.atualizaResultadoAvaliacao(avaliacaoFinanceiraResponse.getResultadoSolicitacao());

        URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private AvaliacaoFinanceiraResponse avaliacaoFinanceira(Proposta novaProposta) {
        AvaliacaoFinanceiraResponse response = null;
        AvaliacaoFinanceiraRequest avaliacaoFinanceiraRequest = new AvaliacaoFinanceiraRequest(novaProposta.getDocumento(),
                novaProposta.getNome(),
                novaProposta.getId().toString());
        try {
            response = avaliacaoFinanceiraClient.avalia(avaliacaoFinanceiraRequest);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                response = new AvaliacaoFinanceiraResponse(novaProposta.getDocumento(),
                        novaProposta.getNome(), ResultadoSolicitacao.COM_RESTRICAO,
                        novaProposta.getId().toString());
            } else {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Serviço de avaliação financeira indisponível");
            }
        }
        return response;
    }


}
