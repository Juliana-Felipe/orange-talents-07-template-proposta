package br.com.zupacademy.proposta.proposta.proposta;

import br.com.zupacademy.proposta.proposta.client.avaliacaoFinanceira.*;
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
public class CadastroPropostaController {

  PropostaRepository propostaRepository;

   private GerarAvaliacao gerarAvaliacao;

    public CadastroPropostaController(PropostaRepository propostaRepository, GerarAvaliacao gerarAvaliacao) {
        this.propostaRepository = propostaRepository;
        this.gerarAvaliacao = gerarAvaliacao;
    }

    @PostMapping
    public ResponseEntity cadastraResposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Proposta novaProposta = request.converte(propostaRepository);
        propostaRepository.save(novaProposta);
        gerarAvaliacao.avaliacaoFinanceira(novaProposta);
        URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
