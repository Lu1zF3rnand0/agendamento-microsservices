package br.com.luiz.agendamentofrontservice.response;

import lombok.Data;

@Data
public class VeiculoResponse {

    private String codigo;
    private String chassi;
    private String modelo;
    private String montadora;
}