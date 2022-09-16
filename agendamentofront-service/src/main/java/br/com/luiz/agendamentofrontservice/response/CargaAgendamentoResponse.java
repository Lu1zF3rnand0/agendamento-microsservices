package br.com.luiz.agendamentofrontservice.response;

import lombok.Data;

@Data
public class CargaAgendamentoResponse {

    private String codigo;
    private String tipoCarga;
    private String descricao;
}
