package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.time.LocalDateTime;

public class Carteira {
     private String id;
     private String email;
     private LocalDateTime associadaEm;
     private String emissor;

     public Carteira() {
     }

     public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
          this.id = id;
          this.email = email;
          this.associadaEm = associadaEm;
          this.emissor = emissor;
     }
}
