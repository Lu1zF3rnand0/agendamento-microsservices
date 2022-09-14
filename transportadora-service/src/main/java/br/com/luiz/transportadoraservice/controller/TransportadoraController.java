package br.com.luiz.transportadoraservice.controller;

import br.com.luiz.transportadoraservice.domain.model.Transportadora;
import br.com.luiz.transportadoraservice.domain.service.TransportadoraService;
import br.com.luiz.transportadoraservice.dto.TransportadoraDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transportadora endpoint")
@RestController
@RequestMapping("transportadora-service")
@AllArgsConstructor
public class TransportadoraController {


    private TransportadoraService transportadoraService;

    private Environment environment;

    @GetMapping("/taok")
    public String getTest() {
        return "Transportadora port " + environment.getProperty("local.server.port");
    }

    @Operation(summary = "Lista todas as transportadoras")
    @GetMapping
    public List<TransportadoraDto> findAllTransportadoras() {
        return transportadoraService.findAllTransportadoras();
    }

    @Operation(summary = "Busca uma transportadora por codigo")
    @GetMapping("/{codigo}")
    public TransportadoraDto findTransportadoraByCodigo(@PathVariable String codigo) {
        Transportadora transportadora = transportadoraService.buscarOuFalhar(codigo);
        return transportadoraService.mapTransportadoraModelToDto(transportadora);
    }

    @Operation(summary = "Cria transportadora")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransportadoraDto create(@RequestBody TransportadoraDto transportadoraDto) {
        Transportadora transportadora = transportadoraService.mapTransportadoraDtoToModel(transportadoraDto);
        return transportadoraService.mapTransportadoraModelToDto(transportadoraService.save(transportadora));
    }

    @Operation(summary = "Altera uma transportadora por codigo")
    @PutMapping("/{codigo}")
    public TransportadoraDto update(@PathVariable String codigo, @RequestBody TransportadoraDto transportadoraDto) {
        Transportadora transportadoraAtual = transportadoraService.buscarOuFalhar(codigo);
        transportadoraService.copyToDomainObject(transportadoraDto, transportadoraAtual);
        return transportadoraService.mapTransportadoraModelToDto(transportadoraService.save(transportadoraAtual));
    }

    @Operation(summary = "Exclui uma transportadora por codigo")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        transportadoraService.deleteByCodigo(codigo);
    }

}
