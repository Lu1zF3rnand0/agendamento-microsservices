package br.com.luiz.veiculoservice.controller;

import br.com.luiz.veiculoservice.domain.model.Veiculo;
import br.com.luiz.veiculoservice.domain.service.VeiculoService;
import br.com.luiz.veiculoservice.dto.VeiculoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Veiculo endpoint")
@RestController
@RequestMapping("veiculo-service")
@AllArgsConstructor
public class VeiculoController {


    private VeiculoService veiculoService;

    private Environment environment;

    @GetMapping("/taok")
    public String getVeiculo() {
        return "Ta funfando em " + environment.getProperty("local.server.port");
    }

    @Operation(summary = "Lista todos os veiculos")
    @GetMapping
    public List<VeiculoDto> findAllVeiculos() {
        return veiculoService.findAllVeiculos();
    }

    @Operation(summary = "Busca um veiculo por codigo ")
    @GetMapping("/{codigo}")
    public VeiculoDto findVeiculoByCodigo(@PathVariable String codigo) {
        Veiculo veiculo = veiculoService.buscarOuFalhar(codigo);
        return veiculoService.mapVeiculoModelToDto(veiculo);
    }

    @Operation(summary = "Cria veiculo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoDto create(@RequestBody VeiculoDto veiculoDto) {
        Veiculo veiculo = veiculoService.mapVeiculoDtoToModel(veiculoDto);
        return veiculoService.mapVeiculoModelToDto(veiculoService.save(veiculo));
    }

    @Operation(summary = "Altera um veiculo por codigo")
    @PutMapping("/{codigo}")
    public VeiculoDto update(@PathVariable String codigo, @RequestBody VeiculoDto veiculoDto) {
        Veiculo veiculoAtual = veiculoService.buscarOuFalhar(codigo);
        veiculoService.copyToDomainObject(veiculoDto, veiculoAtual);
        return veiculoService.mapVeiculoModelToDto(veiculoService.save(veiculoAtual));
    }

    @Operation(summary = "Exclui um veiculo por codigo")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        veiculoService.deleteByCodigo(codigo);
    }

}
