package br.com.luiz.agendamentoservice.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @OneToMany
    private List<Carga> cargas = new ArrayList<>();

    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Transportadora> transportadoras = new ArrayList<>();

    private String descricao;

    private String status = "Agendado";

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

}
