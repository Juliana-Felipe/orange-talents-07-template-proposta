package br.com.zupacademy.proposta.proposta.proposta;

import br.com.zupacademy.proposta.proposta.config.CPFOrCNPJ;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Optional;

public class PropostaRequest {

    @CPFOrCNPJ
    @NotBlank
    private String documento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String rua;
    @NotNull
    private int numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
    @NotNull
    @Positive
    private BigDecimal salario;

    @JsonCreator

    public PropostaRequest(String documento, String email, String nome, String rua, int numero, String complemento, String bairro, String cidade, String estado, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String limpa(String documento) {
        return documento.replace(".", "").replace("-", "").replace("/", "");
    }

    public Proposta converte(PropostaRepository propostaRepository) {
        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(limpa(documento));
        if (possivelProposta.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Uma proposta com esse documento já existe!");
        }
        return new Proposta(limpa(documento), email, nome, new Endereco(rua, numero, complemento, bairro, cidade, estado), salario);
    }

}
