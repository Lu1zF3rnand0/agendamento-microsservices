package br.com.luiz.agendamentoservice.dto;

import br.com.luiz.agendamentoservice.domain.model.Carga;
import br.com.luiz.agendamentoservice.domain.model.Transportadora;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDto {

    private String codigo;
    private String numero;
    private List<Carga> cargas = new ArrayList<>();
    private List<Transportadora> transportadoras = new ArrayList<>();
    private String descricao;
    private String status;
}
