package br.com.luiz.agendamentofrontservice.controller;

import br.com.luiz.agendamentofrontservice.response.TransportadoraResponse;
import br.com.luiz.agendamentofrontservice.service.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransportadoraController {

    @Autowired
    TransportadoraService transportadoraService;

    @GetMapping(value = "/transportadoras")
    public String telaListaTransportadoraes(Model model) {
        model.addAttribute("lista", transportadoraService.listaTransportadoras());
        return "transportadora/transportadoras";
    }

    @GetMapping(value = "/transportadora/excluir/{codigo}")
    public String excluirTransportadora(@PathVariable("codigo") String codigo) {
        transportadoraService.excluirTransportadora(codigo);
        return "redirect:/transportadoras";
    }

    @GetMapping(value = "/transportadora")
    public String telaCadastroTransportadora(Model model) {
        model.addAttribute("transportadora", new TransportadoraResponse());
        return "transportadora/cadastro";
    }

    @PostMapping(value = "/transportadora/incluir")
    public String incluirTransportadora(TransportadoraResponse transportadora) {
        transportadoraService.incluirTransportadora(transportadora);
        return "redirect:/transportadoras";
    }


}
