package br.com.zupacademy.proposta.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/detalhamento")
public class DetalhamentoPropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    public DetalhamentoPropostaController(PropostaRepository propostaRepositorye) {
        this.propostaRepository = propostaRepository;

    }

    @GetMapping
    public ResponseEntity<PropostaResponse> buscaProposta(@RequestParam Long id){
        Optional<Proposta> possivelProposta = propostaRepository.findById(id);
        if(possivelProposta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado");
        }
        Proposta propostaEncontrada = possivelProposta.get();
        PropostaResponse response = new PropostaResponse(propostaEncontrada);
        return ResponseEntity.ok(response);
    }
}
