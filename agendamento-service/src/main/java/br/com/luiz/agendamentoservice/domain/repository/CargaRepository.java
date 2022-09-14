package br.com.luiz.agendamentoservice.domain.repository;

import br.com.luiz.agendamentoservice.domain.model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CargaRepository extends JpaRepository<Carga, Long> {

    Optional<Carga> findByCodigo(String codigo);
}
