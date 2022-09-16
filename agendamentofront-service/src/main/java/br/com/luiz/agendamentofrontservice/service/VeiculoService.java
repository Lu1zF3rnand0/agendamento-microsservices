package br.com.luiz.agendamentofrontservice.service;

import br.com.luiz.agendamentofrontservice.client.VeiculoClient;
import br.com.luiz.agendamentofrontservice.response.VeiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    VeiculoClient veiculoClient;

    public List<VeiculoResponse> listaVeiculos() {
        return veiculoClient.veiculoList();
    }

    public void excluirVeiculo(String codigo) {
        veiculoClient.deleteVeiculo(codigo);
    }

    public void incluirVeiculo(VeiculoResponse veiculo) {
        veiculoClient.createVeiculo(veiculo);
    }


}
