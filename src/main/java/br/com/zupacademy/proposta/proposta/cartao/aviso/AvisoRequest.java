package br.com.zupacademy.proposta.proposta.cartao.aviso;

import br.com.zupacademy.proposta.proposta.cartao.Cartao;
import br.com.zupacademy.proposta.proposta.cartao.CartaoRepository;
import br.com.zupacademy.proposta.proposta.client.consultaCartao.AvisoResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;


public class AvisoRequest {
    @Future
    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;
    @NotBlank
    private String destino;

    @JsonCreator
    public AvisoRequest(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso convert(CartaoRepository cartaoRepository, String id, String userAgent, String ipUsuário) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(Long.parseLong(id));
        if (possivelCartao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado!");
        }
        return new Aviso(validoAte, destino, possivelCartao.get(), userAgent, ipUsuário);
    }
}
