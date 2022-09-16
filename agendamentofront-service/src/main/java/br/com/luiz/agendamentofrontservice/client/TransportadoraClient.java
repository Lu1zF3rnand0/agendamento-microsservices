package br.com.luiz.agendamentofrontservice.client;

import br.com.luiz.agendamentofrontservice.response.TransportadoraResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${transportadoraurl}", name = "transportadoraClient")
public interface TransportadoraClient {

    @GetMapping
    List<TransportadoraResponse> transportadoraList();

    @DeleteMapping("/{codigo}")
    void deleteTransportadora(@PathVariable String codigo);

    @PostMapping
    void createTransportadora(@RequestBody TransportadoraResponse transportadoraResponse);
}
