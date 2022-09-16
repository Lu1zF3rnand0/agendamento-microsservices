package br.com.luiz.agendamentofrontservice.service;

import br.com.luiz.agendamentofrontservice.client.AgendamentoClient;
import br.com.luiz.agendamentofrontservice.response.AgendamentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    AgendamentoClient agendamentoClient;

    public List<AgendamentoResponse> listaAgendamentos() {
        return agendamentoClient.agendamentoList();
    }

    public AgendamentoResponse buscaAgendamento(String codigo) {
        return agendamentoClient.findAgendamentoByCodigo(codigo);
    }

    public void cancelarAgendamento(String codigo) {
        agendamentoClient.cancelaAgendamento(codigo);
    }

    public void incluiAgendamento(AgendamentoResponse agendamento) {
        agendamentoClient.create(agendamento);
    }


}
