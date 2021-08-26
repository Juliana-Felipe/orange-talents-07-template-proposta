package br.com.zupacademy.proposta.proposta.cartao.bloqueio;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

public class BloqueioRequest {
    @NotNull
    private Long cartaoId;
    @NotBlank
    private String userAgent;
    @NotBlank
    private String ipUsuário;

    public BloqueioRequest(@NotNull Long cartaoId,
                           @NotBlank String userAgent,
                           @NotBlank String ipUsuário) {
        this.cartaoId = cartaoId;
        this.userAgent = userAgent;
        this.ipUsuário = ipUsuário;
    }

    public Bloqueio convert(CartaoRepository cartaoRepository) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(cartaoId);
        if (possivelCartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado!");
         }
        Cartao cartao = possivelCartao.get();
        return new Bloqueio(cartao, userAgent, ipUsuário);
    }

    public void verificaBloqueio(BloqueioRepository bloqueioRepository) {
        Optional<Bloqueio> possivelBloqueio = bloqueioRepository.findByCartao_Id(cartaoId);
        if (possivelBloqueio.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe bloqueio para este cartão");
        }
    }
}
