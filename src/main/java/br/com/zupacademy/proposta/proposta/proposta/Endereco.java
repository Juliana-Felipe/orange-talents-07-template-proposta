package br.com.zupacademy.proposta.proposta.proposta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Endereco {
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

    public Endereco(String rua, int numero, String complemento, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Deprecated
    public Endereco() {
    }
}
