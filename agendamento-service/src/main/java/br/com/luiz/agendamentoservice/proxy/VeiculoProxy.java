package br.com.luiz.agendamentoservice.proxy;

import br.com.luiz.agendamentoservice.response.VeiculoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "veiculo-service")
public interface VeiculoProxy {

    @GetMapping("/veiculo-service/{codigo}")
    VeiculoResponse findVeiculoByCodigo(@PathVariable String codigo);
}
