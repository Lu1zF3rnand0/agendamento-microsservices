package br.com.luiz.agendamentofrontservice.client;

import br.com.luiz.agendamentofrontservice.response.CargaSoltaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${cargasoltaurl}", name = "cargaSoltaClient")
public interface CargaSoltaClient {

    @GetMapping
    List<CargaSoltaResponse> cargaSoltaList();

    @DeleteMapping("/{codigo}")
    void deleteCargaSolta(@PathVariable String codigo);

    @PostMapping
    void createCargaSolta(@RequestBody CargaSoltaResponse cargaSoltaResponse);
}
