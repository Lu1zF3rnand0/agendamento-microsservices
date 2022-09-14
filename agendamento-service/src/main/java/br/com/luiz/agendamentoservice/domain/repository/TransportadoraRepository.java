package br.com.luiz.agendamentoservice.domain.repository;

import br.com.luiz.agendamentoservice.domain.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    Optional<Transportadora> findByCodigo(String codigo);

}
