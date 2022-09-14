package br.com.luiz.agendamentoservice.proxy;

import br.com.luiz.agendamentoservice.response.ConteinerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "conteiner-service")
public interface ConteinerProxy {

    @GetMapping("/conteiner-service/{codigo}")
    ConteinerResponse findConteinerByCodigo(@PathVariable String codigo);
}
