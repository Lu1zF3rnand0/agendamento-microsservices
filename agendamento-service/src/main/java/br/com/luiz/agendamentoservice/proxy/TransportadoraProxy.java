package br.com.luiz.agendamentoservice.proxy;

import br.com.luiz.agendamentoservice.response.TransportadoraResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "transportadora-service")
public interface TransportadoraProxy {

    @GetMapping("transportadora-service/{codigo}")
    TransportadoraResponse findTransportadoraByCodigo(@PathVariable String codigo);

    @GetMapping("transportadora-service/taok")
    String getTest();
}
