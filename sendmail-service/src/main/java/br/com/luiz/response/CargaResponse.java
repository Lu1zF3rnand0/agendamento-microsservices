package br.com.luiz.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CargaResponse {

    private String codigo;
    private String tipoCarga;
    private String descricao;
}
