package br.com.luiz.agendamentofrontservice.controller;

import br.com.luiz.agendamentofrontservice.response.AgendamentoResponse;
import br.com.luiz.agendamentofrontservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;


    @GetMapping(value = "/agendamentos")
    public String telaListaAgendamentos(Model model){
        model.addAttribute("lista", agendamentoService.listaAgendamentos() );
        return "agendamento/agendamentos";
    }
//
//    @GetMapping(value = "/agendamento")
//    public String telaCadastroAgendamento(Model model) {
//
//        AgendamentoResponse agendamento = new AgendamentoResponse();
//        model.addAttribute("agendamento", agendamento);
//        model.addAttribute("transportadoras", transportadoraService.listaTransportadoras());
//        model.addAttribute("cargas", cargaSoltaService.listaCargaSoltas());
//        model.addAttribute("conteineres", conteinerService.listaConteineres());
//        model.addAttribute("veiculos", veiculoService.listaVeiculos());
//        return "cadastro2";
//    }


    @PostMapping(value = "/agendamento/incluir")
    public String incluirAgendamento(AgendamentoResponse agendamento){
        agendamentoService.incluiAgendamento(agendamento);

        return "redirect:/agendamentos";
    }


}
