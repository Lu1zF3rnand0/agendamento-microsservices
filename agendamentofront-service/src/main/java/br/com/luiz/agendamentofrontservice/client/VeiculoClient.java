package br.com.luiz.agendamentofrontservice.client;

import br.com.luiz.agendamentofrontservice.response.VeiculoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${veiculourl}", name = "veiculoClient")
public interface VeiculoClient {

    @GetMapping
    List<VeiculoResponse> veiculoList();

    @DeleteMapping("/{codigo}")
    void deleteVeiculo(@PathVariable String codigo);

    @PostMapping
    void createVeiculo(@RequestBody VeiculoResponse veiculoResponse);
}
