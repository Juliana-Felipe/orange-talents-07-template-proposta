package br.com.zupacademy.proposta.proposta.cartao.bloqueio;

import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import br.com.zupacademy.proposta.proposta.client.avisoBloqueio.AvisarBloqueio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartao/{id}/bloqueios")
public class BloqueioController {
    private BloqueioRepository bloqueioRepository;
    private CartaoRepository cartaoRepository;
    private AvisarBloqueio avisarBloqueio;

    public BloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository, AvisarBloqueio avisarBloqueio) {
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoRepository = cartaoRepository;
        this.avisarBloqueio = avisarBloqueio;
    }



    @PostMapping
    public ResponseEntity<?> cadastraBloqueio(@PathVariable Long id, @RequestHeader("User-Agent") String userAgent, @RequestHeader("X-Forward-For") String xForwardFor) {
        if(userAgent.isBlank()||xForwardFor.isBlank()){
            return ResponseEntity.badRequest().body("User-Agent ou X-Forward-For estão em branco.");
        }

        String enderecoIp = xForwardFor.split(",")[0];
        BloqueioRequest bloqueioRequest = new BloqueioRequest(id, userAgent, enderecoIp);
        bloqueioRequest.verificaBloqueio(bloqueioRepository);
        Bloqueio bloqueio = bloqueioRequest.convert(cartaoRepository);
        bloqueio.getCartao().alterarEstadoDoCartao(EstadoDoCartao.BLOQUEIO_SOLICITADO);
        avisarBloqueio.avisoImediato(bloqueio.getCartao());
        bloqueioRepository.save(bloqueio);
        return ResponseEntity.ok().build();
    }


}

