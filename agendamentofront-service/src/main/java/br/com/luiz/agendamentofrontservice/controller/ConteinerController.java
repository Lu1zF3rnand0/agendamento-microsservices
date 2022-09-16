package br.com.luiz.agendamentofrontservice.controller;

import br.com.luiz.agendamentofrontservice.response.ConteinerResponse;
import br.com.luiz.agendamentofrontservice.service.ConteinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConteinerController {

    @Autowired
    ConteinerService conteinerService;

    @GetMapping(value = "/conteineres")
    public String telaListaConteineres(Model model){
        model.addAttribute("lista", conteinerService.listaConteineres() );
        return "conteiner/conteineres";
    }

    @GetMapping(value = "/conteiner/excluir/{codigo}")
    public String excluirConteiner(@PathVariable("codigo") String codigo){
        conteinerService.excluirConteiner(codigo);
        return "redirect:/conteineres";
    }

    @GetMapping(value = "/conteiner")
    public String telaCadastroConteiner(Model model){
        model.addAttribute("conteiner", new ConteinerResponse());
        return "conteiner/cadastro";
    }

    @PostMapping(value = "/conteiner/incluir")
    public String incluirConteiner(ConteinerResponse conteiner){
        conteinerService.incluirConteiner(conteiner);
        return "redirect:/conteineres";
    }


}
