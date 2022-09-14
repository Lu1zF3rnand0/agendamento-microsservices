package br.com.luiz.agendamentoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class CargaDto {

    private String codigo;
    private String tipoCarga;
    private String descricao;
}
