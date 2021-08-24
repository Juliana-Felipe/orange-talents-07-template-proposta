package br.com.zupacademy.proposta.proposta.biometria;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.Optional;

public class BiometriaRequest {

    @NotBlank
    private String fingerPrint;

    private String cartao_id;

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Biometria converte(String cartao_id, CartaoRepository cartaoRepository) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(Long.parseLong(cartao_id));
        if (possivelCartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não existe!");
        }
        validarBase64();
        Cartao cartao = possivelCartao.get();
        return new Biometria(fingerPrint, cartao);
    }

    private void validarBase64() {
        Base64.Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(fingerPrint);
        } catch(IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de biometria inválido.");
        }
    }
}
