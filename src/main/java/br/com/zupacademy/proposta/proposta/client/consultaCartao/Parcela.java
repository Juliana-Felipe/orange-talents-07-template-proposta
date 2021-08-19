package br.com.zupacademy.proposta.proposta.client.consultaCartao;

public class Parcela {
   private String id;
   private int quantidade;
   private double valor;

     public Parcela() {
     }

     public Parcela(String id, int quantidade, double valor) {
          this.id = id;
          this.quantidade = quantidade;
          this.valor = valor;
     }
}
