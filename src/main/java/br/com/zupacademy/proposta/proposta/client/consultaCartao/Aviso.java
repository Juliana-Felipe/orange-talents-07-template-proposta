package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.util.Date;

public class Aviso {
     private Date validoAte;
     private String destino;

     public Aviso() {
     }

     public Aviso(Date validoAte, String destino) {
          this.validoAte = validoAte;
          this.destino = destino;
     }
}
