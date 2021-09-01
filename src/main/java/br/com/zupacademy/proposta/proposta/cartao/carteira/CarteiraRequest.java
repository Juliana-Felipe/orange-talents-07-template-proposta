package br.com.zupacademy.proposta.proposta.cartao.carteira;


import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CarteiraRequest {
    @NotBlank
    @Email
    private String email;
    @NotNull
    private TipoCarteira tipoCarteira;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraRequest(String email, TipoCarteira tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public Carteira convert(CarteiraRepository carteiraRepository, Long id, CartaoRepository cartaoRepository) {
        Cartao cartao = verificaCartao(id, cartaoRepository);
        verificaCarteiraAssociada(carteiraRepository, id);
        return new Carteira(email, tipoCarteira, cartao);

    }

    private Cartao verificaCartao(Long id, CartaoRepository cartaoRepository) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if(possivelCartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse cartão não existe");
        }
        return possivelCartao.get();
    }

    private void verificaCarteiraAssociada(CarteiraRepository carteiraRepository, Long id) {
        Optional<Carteira> possivelCarteira = carteiraRepository.findByEmissorAndCartaoId(tipoCarteira, id);
        if(possivelCarteira.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão de id: " + id + " já está associado a carteira " + tipoCarteira);
        }
    }
}
