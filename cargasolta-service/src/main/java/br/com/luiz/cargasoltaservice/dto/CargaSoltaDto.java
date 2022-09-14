package br.com.luiz.cargasoltaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class CargaSoltaDto {

    private String codigo;
    private String nome;
    private String descricao;
    private boolean imo;
}
