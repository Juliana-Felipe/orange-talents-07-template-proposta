package br.com.zupacademy.proposta.proposta.client.avisoAvisoDeViagem;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AvisoDeViagemRequest {
    SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");

    private String destino;
    private String validoAte;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoDeViagemRequest(String destino, Date validoAte) {
        this.destino = destino;
        String data = formatador.format(validoAte);
        this.validoAte = data;
    }

    public String getDestino() {
        return destino;
    }

    public String getValidoAte() {
        return validoAte;
    }
}
