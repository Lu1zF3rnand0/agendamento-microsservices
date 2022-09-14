package br.com.luiz.agendamentoservice.proxy;

import br.com.luiz.agendamentoservice.response.CargaSoltaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cargasolta-service")
public interface CargaSoltaProxy {

    @GetMapping("/cargasolta-service/{codigo}")
    CargaSoltaResponse findCargaSoltaByCodigo(@PathVariable String codigo);
}
