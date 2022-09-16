package br.com.luiz.agendamentofrontservice.controller;

import br.com.luiz.agendamentofrontservice.response.CargaSoltaResponse;
import br.com.luiz.agendamentofrontservice.service.CargaSoltaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CargaSoltaController {

    @Autowired
    CargaSoltaService cargaSoltaService;

    @GetMapping(value = "/cargassoltas")
    public String telaListaCargaSoltas(Model model) {
        model.addAttribute("lista", cargaSoltaService.listaCargaSoltas());
        return "cargasolta/cargassoltas";
    }

    @GetMapping(value = "/cargasolta/excluir/{codigo}")
    public String excluirCargaSolta(@PathVariable("codigo") String codigo) {
        cargaSoltaService.excluirCargaSolta(codigo);
        return "redirect:/cargassoltas";
    }

    @GetMapping(value = "/cargasolta")
    public String telaCadastroCargaSolta(Model model) {
        model.addAttribute("cargasolta", new CargaSoltaResponse());
        return "cargasolta/cadastro";
    }

    @PostMapping(value = "/cargasolta/incluir")
    public String incluirCargaSolta(CargaSoltaResponse cargaSolta) {
        cargaSoltaService.incluirCargaSolta(cargaSolta);
        return "redirect:/cargassoltas";
    }


}
