package br.com.zupacademy.proposta.proposta.biometria;

import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {
    private CartaoRepository cartaoRepository;
    private BiometriaRepository biometriaRepository;

    public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/{cartao_id}")
    ResponseEntity cadastrarBiometria(@PathVariable("cartao_id") String cartao_id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriComponentsBuilder) {
       Biometria novaBiometria = request.converte(cartao_id, cartaoRepository);
       biometriaRepository.save(novaBiometria);
       URI uri = uriComponentsBuilder.path("/biometria/{cartao_id}/{id}").buildAndExpand(novaBiometria.getCartao().getId(), novaBiometria.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

}
