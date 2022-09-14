package br.com.luiz.cargasoltaservice.controller;

import br.com.luiz.cargasoltaservice.domain.model.CargaSolta;
import br.com.luiz.cargasoltaservice.domain.service.CargaSoltaService;
import br.com.luiz.cargasoltaservice.dto.CargaSoltaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carga Solta endpoint")
@RestController
@RequestMapping("cargasolta-service")
@AllArgsConstructor
public class CargaSoltaController {


    private CargaSoltaService cargaSoltaService;

    private Environment environment;

    @GetMapping("/taok")
    public String getCargaSolta() {
        return "Ta funfando em " + environment.getProperty("local.server.port");
    }

    @Operation(summary = "Lista todas as cargas soltas")
    @GetMapping
    public List<CargaSoltaDto> findAllCargaSoltas() {
        return cargaSoltaService.findAllCargaSoltas();
    }

    @Operation(summary = "Busca um carga solta por codigo")
    @GetMapping("/{codigo}")
    public CargaSoltaDto findCargaSoltaByCodigo(@PathVariable String codigo) {
        CargaSolta cargaSolta = cargaSoltaService.buscarOuFalhar(codigo);
        return cargaSoltaService.mapCargaSoltaModelToDto(cargaSolta);
    }

    @Operation(summary = "Cria carga solta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CargaSoltaDto create(@RequestBody CargaSoltaDto cargaSoltaDto) {
        CargaSolta cargaSolta = cargaSoltaService.mapCargaSoltaDtoToModel(cargaSoltaDto);
        return cargaSoltaService.mapCargaSoltaModelToDto(cargaSoltaService.save(cargaSolta));
    }

    @Operation(summary = "Altera uma carga solta por codigo")
    @PutMapping("/{codigo}")
    public CargaSoltaDto update(@PathVariable String codigo, @RequestBody CargaSoltaDto cargaSoltaDto) {
        CargaSolta cargaSoltaAtual = cargaSoltaService.buscarOuFalhar(codigo);
        cargaSoltaService.copyToDomainObject(cargaSoltaDto, cargaSoltaAtual);
        return cargaSoltaService.mapCargaSoltaModelToDto(cargaSoltaService.save(cargaSoltaAtual));
    }

    @Operation(summary = "Exclui uma carga solta por codigo")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        cargaSoltaService.deleteByCodigo(codigo);
    }

}
