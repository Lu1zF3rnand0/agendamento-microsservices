package br.com.luiz.agendamentofrontservice.client;

import br.com.luiz.agendamentofrontservice.response.ConteinerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${conteinerurl}", name = "conteinerClient")
public interface ConteinerClient {

    @GetMapping
    List<ConteinerResponse> conteinerList();

    @DeleteMapping("/{codigo}")
    void deleteConteiner(@PathVariable String codigo);

    @PostMapping
    void createConteiner(@RequestBody ConteinerResponse conteinerResponse);
}
