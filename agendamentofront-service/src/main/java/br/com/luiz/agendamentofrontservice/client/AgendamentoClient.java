package br.com.luiz.agendamentofrontservice.client;

import br.com.luiz.agendamentofrontservice.response.AgendamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${agendamentourl}", name = "agendamentoClient")
public interface AgendamentoClient {

    @GetMapping
    List<AgendamentoResponse> agendamentoList();


    @GetMapping("/{codigo}")
    AgendamentoResponse findAgendamentoByCodigo(@PathVariable String codigo);

    @DeleteMapping("/cancela/{codigo}")
    void cancelaAgendamento(@PathVariable String codigo);

    @PostMapping
    AgendamentoResponse create(@RequestBody AgendamentoResponse agendamentoDto);
}
