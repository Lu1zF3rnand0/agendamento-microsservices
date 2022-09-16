package br.com.luiz.agendamentofrontservice.controller;

import br.com.luiz.agendamentofrontservice.response.VeiculoResponse;
import br.com.luiz.agendamentofrontservice.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VeiculoController {

    @Autowired
    VeiculoService veiculoService;

    @GetMapping(value = "/veiculos")
    public String telaListaVeiculoes(Model model){
        model.addAttribute("lista", veiculoService.listaVeiculos() );
        return "veiculo/veiculos";
    }

    @GetMapping(value = "/veiculo/excluir/{codigo}")
    public String excluirVeiculo(@PathVariable("codigo") String codigo){
        veiculoService.excluirVeiculo(codigo);
        return "redirect:/veiculos";
    }

    @GetMapping(value = "/veiculo")
    public String telaCadastroVeiculo(Model model){
        model.addAttribute("veiculo", new VeiculoResponse());
        return "veiculo/cadastro";
    }

    @PostMapping(value = "/veiculo/incluir")
    public String incluirVeiculo(VeiculoResponse veiculo){
        veiculoService.incluirVeiculo(veiculo);
        return "redirect:/veiculos";
    }


}
