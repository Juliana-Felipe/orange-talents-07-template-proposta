package br.com.zupacademy.proposta.proposta.proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity cadastraResposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
        Proposta novaProposta = request.converte();
        propostaRepository.save(novaProposta);
        URI uri = uriComponentsBuilder.path("/proposta/{id}").buildAndExpand(novaProposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
