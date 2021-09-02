package br.com.zupacademy.proposta.proposta.cartao.carteira;

import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.client.associarCarteira.AssociarCarteira;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("cartao/{id}/carteira")
public class CarteiraController {
private CarteiraRepository carteiraRepository;
private CartaoRepository cartaoRepository;
private AssociarCarteira associarCarteira;

    public CarteiraController(CarteiraRepository carteiraRepository, CartaoRepository cartaoRepository,AssociarCarteira associarCarteira) {
        this.carteiraRepository = carteiraRepository;
        this.cartaoRepository = cartaoRepository;
        this.associarCarteira = associarCarteira;
    }

    @PostMapping
    public ResponseEntity cadastraCarteira(@PathVariable Long id, @RequestBody @Valid CarteiraRequest request, UriComponentsBuilder uriComponentsBuilder){
        Carteira carteira = request.convert(carteiraRepository, id, cartaoRepository);
        carteiraRepository.save(carteira);
        associarCarteira.associacaoImediata(carteira);
        URI uri = uriComponentsBuilder.path("cartao/{id}/carteira/{id_Carteira}").buildAndExpand(carteira.getCartao().getId(), carteira.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
