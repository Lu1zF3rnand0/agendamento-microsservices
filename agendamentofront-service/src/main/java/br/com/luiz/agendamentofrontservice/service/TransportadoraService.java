package br.com.luiz.agendamentofrontservice.service;

import br.com.luiz.agendamentofrontservice.client.TransportadoraClient;
import br.com.luiz.agendamentofrontservice.response.TransportadoraResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraService {

    @Autowired
    TransportadoraClient transportadoraClient;

    public List<TransportadoraResponse> listaTransportadoras() {
        return transportadoraClient.transportadoraList();
    }

    public void excluirTransportadora(String codigo) {
        transportadoraClient.deleteTransportadora(codigo);
    }

    public void incluirTransportadora(TransportadoraResponse transportadora) {
        transportadoraClient.createTransportadora(transportadora);
    }


}
