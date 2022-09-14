package br.com.luiz.cargasoltaservice.domain.repository;

import br.com.luiz.cargasoltaservice.domain.model.CargaSolta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargaSoltaRepository extends JpaRepository<CargaSolta, Long> {

    Optional<CargaSolta> findByCodigo(String codigo);


}
