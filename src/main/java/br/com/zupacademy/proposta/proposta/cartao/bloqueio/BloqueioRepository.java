package br.com.zupacademy.proposta.proposta.cartao.bloqueio;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BloqueioRepository extends CrudRepository<Bloqueio,Long> {
    Optional<Bloqueio> findByCartao_Id(Long id);
}
