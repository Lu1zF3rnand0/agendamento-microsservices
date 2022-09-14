package br.com.luiz.conteinerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ConteinerDto {

    private String codigo;
    private String numero;
    private int tamanho;
    private String status;
}
