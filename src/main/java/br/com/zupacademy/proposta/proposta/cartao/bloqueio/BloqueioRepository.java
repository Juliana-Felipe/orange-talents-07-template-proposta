package br.com.zupacademy.proposta.proposta.cartao.bloqueio;

import br.com.zupacademy.proposta.proposta.cartao.EstadoDoCartao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BloqueioRepository extends CrudRepository<Bloqueio,Long> {
    Optional<Bloqueio> findByCartao_Id(Long id);
    Bloqueio findBySistemaResponsavel(String sistema);
}
