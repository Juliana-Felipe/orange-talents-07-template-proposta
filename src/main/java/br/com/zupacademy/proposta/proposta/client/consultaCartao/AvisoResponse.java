package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.time.LocalDate;
import java.util.Date;

public class AvisoResponse {
     private LocalDate validoAte;
     private String destino;

     public AvisoResponse() {
     }

     public AvisoResponse(LocalDate validoAte, String destino) {
          this.validoAte = validoAte;
          this.destino = destino;
     }

     public LocalDate getValidoAte() {
          return validoAte;
     }

     public String getDestino() {
          return destino;
     }
}
