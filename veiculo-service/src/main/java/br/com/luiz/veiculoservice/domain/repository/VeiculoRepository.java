package br.com.luiz.veiculoservice.domain.repository;

import br.com.luiz.veiculoservice.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByCodigo(String codigo);


}
