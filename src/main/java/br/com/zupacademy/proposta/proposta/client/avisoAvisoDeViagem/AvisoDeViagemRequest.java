package br.com.zupacademy.proposta.proposta.client.avisoAvisoDeViagem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class AvisoDeViagemRequest {

    private String destino;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoDeViagemRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
