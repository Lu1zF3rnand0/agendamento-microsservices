package br.com.luiz.agendamentofrontservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class TransportadoraAgendamentoResponse {

    private String codigo;
    private String nome;
    private String email;
}
