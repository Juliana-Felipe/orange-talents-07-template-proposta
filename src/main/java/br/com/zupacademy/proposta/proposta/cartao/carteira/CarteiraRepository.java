package br.com.zupacademy.proposta.proposta.cartao.carteira;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarteiraRepository extends CrudRepository<Carteira,Long> {
    Optional<Carteira> findByEmissorAndCartaoId(TipoCarteira tipoCarteira, Long id);

    List<Carteira> findByEstadoDaAssociacao(EstadoDaAssociacao estadoDaAssociacao);
}
