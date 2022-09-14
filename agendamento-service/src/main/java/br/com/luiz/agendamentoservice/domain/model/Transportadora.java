package br.com.luiz.agendamentoservice.domain.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Transportadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String email;


}
