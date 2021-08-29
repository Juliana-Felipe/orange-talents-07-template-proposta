package br.com.zupacademy.proposta.proposta.cartao.aviso;

import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRepository;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRequest;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisarBloqueio;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisoBloqueioRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartao/{id}/avisos")
public class AvisoController {

private CartaoRepository cartaoRepository;
private AvisoRepository avisoRepository;

    public AvisoController(CartaoRepository cartaoRepository, AvisoRepository avisoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoRepository = avisoRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastraAviso(@PathVariable String id, @RequestHeader("User-Agent") String userAgent, @RequestHeader("X-Forward-For") String xForwardFor, @RequestBody @Valid AvisoRequest avisoRequest, UriComponentsBuilder uriComponentsBuilder) {
        if(userAgent.isBlank()||xForwardFor.isBlank()){
            return ResponseEntity.badRequest().body("User-Agent ou X-Forward-For estão em branco.");
        }
        String enderecoIp = xForwardFor.split(",")[0];
        Aviso aviso = avisoRequest.convert(cartaoRepository, id, userAgent, enderecoIp);

        avisoRepository.save(aviso);
        URI uri = uriComponentsBuilder.path("/cartao/{id}/avisos/{id_Aviso}").buildAndExpand(aviso.getCartao().getId(), aviso.getId()).toUri();
        return ResponseEntity.ok(uri);
    }


}