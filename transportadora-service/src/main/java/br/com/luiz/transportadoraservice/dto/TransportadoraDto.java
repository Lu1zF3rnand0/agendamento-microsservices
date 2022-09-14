package br.com.luiz.transportadoraservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class TransportadoraDto {

    private String codigo;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;

}