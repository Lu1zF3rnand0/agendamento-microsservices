package br.com.luiz.agendamentofrontservice.service;

import br.com.luiz.agendamentofrontservice.client.ConteinerClient;
import br.com.luiz.agendamentofrontservice.response.ConteinerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteinerService {

    @Autowired
    ConteinerClient conteinerClient;

    public List<ConteinerResponse> listaConteineres() {
        return conteinerClient.conteinerList();
    }

    public void excluirConteiner(String codigo) {
        conteinerClient.deleteConteiner(codigo);
    }

    public void incluirConteiner(ConteinerResponse conteiner) {
        conteinerClient.createConteiner(conteiner);
    }


}
