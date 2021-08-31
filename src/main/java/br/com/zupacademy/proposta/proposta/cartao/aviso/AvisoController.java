package br.com.zupacademy.proposta.proposta.cartao.aviso;

import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import br.com.zupacademy.proposta.proposta.cartao.NotificacaoDeAviso;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.Bloqueio;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRepository;
import br.com.zupacademy.proposta.proposta.cartao.bloqueio.BloqueioRequest;
import br.com.zupacademy.proposta.proposta.client.avisoAvisoDeViagem.AvisarSobreAvisoDeViagem;
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
    private AvisarSobreAvisoDeViagem avisarSobreAvisoDeViagem;

    public AvisoController(CartaoRepository cartaoRepository, AvisoRepository avisoRepository, AvisarSobreAvisoDeViagem avisarSobreAvisoDeViagem) {
        this.cartaoRepository = cartaoRepository;
        this.avisoRepository = avisoRepository;
        this.avisarSobreAvisoDeViagem = avisarSobreAvisoDeViagem;
    }

    @PostMapping
    public ResponseEntity<?> cadastraAviso(@PathVariable String id, @RequestHeader("User-Agent") String userAgent, @RequestHeader("X-Forward-For") String xForwardFor, @RequestBody @Valid AvisoRequest avisoRequest, UriComponentsBuilder uriComponentsBuilder) {
        if (userAgent.isBlank() || xForwardFor.isBlank()) {
            return ResponseEntity.badRequest().body("User-Agent ou X-Forward-For estão em branco.");
        }
        String enderecoIp = xForwardFor.split(",")[0];
        Aviso aviso = avisoRequest.convert(cartaoRepository, id, userAgent, enderecoIp);
        aviso.getCartao().alterarNotificacaoDeAviso(NotificacaoDeAviso.AVISO_SOLICITADO);
        avisarSobreAvisoDeViagem.avisoImediato(aviso);
        avisoRepository.save(aviso);

        URI uri = uriComponentsBuilder.path("/cartao/{id}/avisos/{id_Aviso}").buildAndExpand(aviso.getCartao().getId(), aviso.getId()).toUri();
        return ResponseEntity.ok(uri);
    }


}