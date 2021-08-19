package br.com.zupacademy.proposta.proposta.client.consultaCartao;

import java.time.LocalDateTime;

public class Renegociacao {
     private String id;
     private int quantidade;
     private double valor;
     private LocalDateTime dataDeCriacao;

     public Renegociacao() {
     }

     public Renegociacao(String id, int quantidade, double valor, LocalDateTime dataDeCriacao) {
          this.id = id;
          this.quantidade = quantidade;
          this.valor = valor;
          this.dataDeCriacao = dataDeCriacao;
     }
}
