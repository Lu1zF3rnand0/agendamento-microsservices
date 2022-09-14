package br.com.luiz.transportadoraservice.domain.repository;

import br.com.luiz.transportadoraservice.domain.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    Optional<Transportadora> findByCodigo(String codigo);


}
