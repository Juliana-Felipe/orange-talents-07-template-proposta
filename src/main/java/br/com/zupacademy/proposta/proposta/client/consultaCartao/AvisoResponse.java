package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.util.Date;

public class AvisoResponse {
     private Date validoAte;
     private String destino;

     public AvisoResponse() {
     }

     public AvisoResponse(Date validoAte, String destino) {
          this.validoAte = validoAte;
          this.destino = destino;
     }

     public Date getValidoAte() {
          return validoAte;
     }

     public String getDestino() {
          return destino;
     }
}
