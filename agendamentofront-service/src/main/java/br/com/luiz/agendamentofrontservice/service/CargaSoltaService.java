package br.com.luiz.agendamentofrontservice.service;

import br.com.luiz.agendamentofrontservice.client.CargaSoltaClient;
import br.com.luiz.agendamentofrontservice.response.CargaSoltaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargaSoltaService {

    @Autowired
    CargaSoltaClient cargaSoltaClient;

    public List<CargaSoltaResponse> listaCargaSoltas() {
        return cargaSoltaClient.cargaSoltaList();
    }

    public void excluirCargaSolta(String codigo) {
        cargaSoltaClient.deleteCargaSolta(codigo);
    }

    public void incluirCargaSolta(CargaSoltaResponse cargaSolta) {
        cargaSoltaClient.createCargaSolta(cargaSolta);
    }


}
