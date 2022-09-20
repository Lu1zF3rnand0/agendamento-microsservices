package br.com.luiz.response;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoResponse {

    private String codigo;
    private String numero;
    private List<CargaResponse> cargas = new ArrayList<>();
    private List<TransportadoraResponse> transportadoras = new ArrayList<>();
    private String descricao;
    private String status;
}
