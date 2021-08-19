package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.time.LocalDateTime;

public class Vencimento {
     private String id;
     private int dia;
     private LocalDateTime dataDeCriacao;

     public Vencimento() {
     }

     public Vencimento(String id, int dia, LocalDateTime dataDeCriacao) {
          this.id = id;
          this.dia = dia;
          this.dataDeCriacao = dataDeCriacao;
     }
}
