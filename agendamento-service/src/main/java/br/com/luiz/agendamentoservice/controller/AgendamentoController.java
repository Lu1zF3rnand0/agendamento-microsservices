package br.com.luiz.agendamentoservice.controller;

import br.com.luiz.agendamentoservice.domain.model.Agendamento;
import br.com.luiz.agendamentoservice.domain.model.Carga;
import br.com.luiz.agendamentoservice.domain.model.Transportadora;
import br.com.luiz.agendamentoservice.domain.service.AgendamentoService;
import br.com.luiz.agendamentoservice.domain.service.CargaService;
import br.com.luiz.agendamentoservice.domain.service.TransportadoraService;
import br.com.luiz.agendamentoservice.dto.AgendamentoDto;
import br.com.luiz.agendamentoservice.dto.CargaDto;
import br.com.luiz.agendamentoservice.dto.TransportadoraDto;
import br.com.luiz.agendamentoservice.proxy.TransportadoraProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Agendamento endpoint")
@RestController
@RequestMapping("agendamento-service")
@AllArgsConstructor
public class AgendamentoController {


    private AgendamentoService agendamentoService;

    private CargaService cargaService;

    private TransportadoraService transportadoraService;

    private TransportadoraProxy transportadoraProxy;

    private Environment environment;

    @GetMapping("/taok")
    public String getTest() {
        return transportadoraProxy.getTest() + " Agendamento port: " + environment.getProperty("local.server.port");
//        return "Ta funfando em " + environment.getProperty("local.server.port");
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

        Agendamento agendamento = agendamentoService.mapAgendamentoDtoToModel(agendamentoDto);
        return agendamentoService.mapAgendamentoModelToDto(agendamentoService.save(agendamento));
    }

    @Operation(summary = "Cria carga")
    @PostMapping("/cargas")
    @ResponseStatus(HttpStatus.CREATED)
    public CargaDto create(@RequestBody CargaDto cargaDto) {
        Carga carga = cargaService.mapCargaDtoToModel(cargaDto);
        return cargaService.mapCargaModelToDto(cargaService.save(carga));
    }

    @Operation(summary = "Cria transportadora")
    @PostMapping("/transportadoras")
    @ResponseStatus(HttpStatus.CREATED)
    public TransportadoraDto create(@RequestBody TransportadoraDto transportadoraDto) {
        Transportadora transportadora = transportadoraService.mapTransportadoraDtoToModel(transportadoraDto);
        return transportadoraService.mapTransportadoraModelToDto(transportadoraService.save(transportadora));
    }

    @Operation(summary = "Exclui um agendamento por codigo")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        agendamentoService.deleteByCodigo(codigo);
    }

}
