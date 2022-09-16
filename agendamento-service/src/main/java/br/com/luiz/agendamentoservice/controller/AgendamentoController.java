package br.com.luiz.agendamentoservice.controller;

import br.com.luiz.agendamentoservice.domain.model.Agendamento;
import br.com.luiz.agendamentoservice.domain.model.Carga;
import br.com.luiz.agendamentoservice.domain.model.Transportadora;
import br.com.luiz.agendamentoservice.domain.service.AgendamentoService;
import br.com.luiz.agendamentoservice.domain.service.CargaService;
import br.com.luiz.agendamentoservice.domain.service.TransportadoraService;
import br.com.luiz.agendamentoservice.dto.AgendamentoDto;
import br.com.luiz.agendamentoservice.proxy.TransportadoraProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Tag(name = "Agendamento endpoint")
@RestController
@RequestMapping("agendamento-service")
public class AgendamentoController {


    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private CargaService cargaService;

    @Autowired
    private TransportadoraService transportadoraService;

    @Autowired
    private TransportadoraProxy transportadoraProxy;

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${mail}")
    private String uri;

    @GetMapping("/taok")
    public String getTest() {
        return transportadoraProxy.getTest() + " Agendamento port: " + environment.getProperty("local.server.port");
    }


    @GetMapping
    @Operation(summary = "Lista todos os agendamentos")
    public List<AgendamentoDto> findAllAgendamentos() {
        return agendamentoService.findAllAgendamentos();
    }

    @Operation(summary = "Busca um agendamento por codigo")
    @GetMapping("/{codigo}")
    public AgendamentoDto findAgendamentoByCodigo(@PathVariable String codigo) {
        Agendamento agendamento = agendamentoService.buscarOuFalhar(codigo);
        return agendamentoService.mapAgendamentoModelToDto(agendamento);
    }

    @Operation(summary = "Cria agendamento")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoDto create(@RequestBody AgendamentoDto agendamentoDto) {
        List<Transportadora> transportadoras = transportadoraService.retornaTransportadorasOk(agendamentoDto.getTransportadoras());
        List<Carga> cargas = cargaService.retornaCargasOk(agendamentoDto.getCargas());

        agendamentoDto.setTransportadoras(transportadoras);
        agendamentoDto.setCargas(cargas);
        agendamentoDto.setNumero(String.valueOf(new Random().nextInt(9999)));

        Agendamento agendamento = agendamentoService.mapAgendamentoDtoToModel(agendamentoDto);


        String url = uri + "/create";
        restTemplate.postForEntity(url, agendamentoDto, String.class);

        return agendamentoService.mapAgendamentoModelToDto(agendamentoService.save(agendamento));
    }

    @Operation(summary = "Cancela um agendamento por codigo")
    @DeleteMapping("/cancela/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelAgendamento(@PathVariable String codigo) {
        Agendamento agendamento = agendamentoService.buscarOuFalhar(codigo);
        agendamentoService.cancelaAgendamento(agendamento);
        var agendamentoDto = agendamentoService.mapAgendamentoModelToDto(agendamento);

        String url = uri + "/cancel";
        restTemplate.postForEntity(url, agendamentoDto, String.class);

    }

}
