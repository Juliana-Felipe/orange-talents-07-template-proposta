package br.com.zupacademy.proposta.proposta.cartao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaoRepository extends CrudRepository<Cartao,Long> {
    List<Cartao> findByEstadoDoCartao(EstadoDoCartao bloqueioSolicitado);

    List<Cartao> findByNotificacaoDeAviso(NotificacaoDeAviso avisoSolicitado);
}
