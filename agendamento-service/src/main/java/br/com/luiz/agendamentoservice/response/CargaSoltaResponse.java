package br.com.luiz.agendamentoservice.response;

import lombok.Data;

@Data
public class CargaSoltaResponse {

    private String codigo;
    private String nome;
    private String descricao;
    private boolean imo;
}
