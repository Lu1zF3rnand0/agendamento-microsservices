package br.com.luiz.conteinerservice.controller;

import br.com.luiz.conteinerservice.domain.model.Conteiner;
import br.com.luiz.conteinerservice.domain.service.ConteinerService;
import br.com.luiz.conteinerservice.dto.ConteinerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transportadora endpoint")
@RestController
@RequestMapping("conteiner-service")
@AllArgsConstructor
public class ConteinerController {


    private ConteinerService conteinerService;

    private Environment environment;

    @GetMapping("/taok")
    public String getConteiner() {
        return "Ta funfando em " + environment.getProperty("local.server.port");
    }

    @Operation(summary = "Lista todos os conteineres")
    @GetMapping
    public List<ConteinerDto> findAllConteiners() {
        return conteinerService.findAllConteiners();
    }

    @Operation(summary = "Busca um conteiner por codigo")
    @GetMapping("/{codigo}")
    public ConteinerDto findConteinerByCodigo(@PathVariable String codigo) {
        Conteiner conteiner = conteinerService.buscarOuFalhar(codigo);
        return conteinerService.mapConteinerModelToDto(conteiner);
    }

    @Operation(summary = "Cria conteiner")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConteinerDto create(@RequestBody ConteinerDto conteinerDto) {
        Conteiner conteiner = conteinerService.mapConteinerDtoToModel(conteinerDto);
        return conteinerService.mapConteinerModelToDto(conteinerService.save(conteiner));
    }

    @Operation(summary = "Altera um conteiner por codigo")
    @PutMapping("/{codigo}")
    public ConteinerDto update(@PathVariable String codigo, @RequestBody ConteinerDto conteinerDto) {
        Conteiner conteinerAtual = conteinerService.buscarOuFalhar(codigo);
        conteinerService.copyToDomainObject(conteinerDto, conteinerAtual);
        return conteinerService.mapConteinerModelToDto(conteinerService.save(conteinerAtual));
    }

    @Operation(summary = "Exclui um conteiner por codigo")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String codigo) {
        conteinerService.deleteByCodigo(codigo);
    }

}
