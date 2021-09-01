package br.com.zupacademy.proposta.proposta.client.associarCarteira;

import br.com.zupacademy.proposta.proposta.cartao.carteira.TipoCarteira;
import com.fasterxml.jackson.annotation.JsonCreator;

public class AssociarCarteiraRequest {
    private String email;
    private TipoCarteira carteira;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AssociarCarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
