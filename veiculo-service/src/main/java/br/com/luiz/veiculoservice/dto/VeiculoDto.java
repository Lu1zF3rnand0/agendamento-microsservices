package br.com.luiz.veiculoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDto {

    private String codigo;
    private String chassi;
    private String modelo;
    private String montadora;
}