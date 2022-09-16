package br.com.luiz.agendamentofrontservice.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AgendamentoResponse {

    private String codigo;
    private String numero;
    private List<CargaAgendamentoResponse> cargas = new ArrayList<>();
    private List<TransportadoraAgendamentoResponse> transportadoras = new ArrayList<>();
    private String descricao;
    private String status;
}
