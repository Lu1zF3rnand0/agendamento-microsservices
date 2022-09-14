package br.com.luiz.agendamentoservice.response;

import lombok.Data;

@Data
public class ConteinerResponse {

    private String codigo;
    private String numero;
    private int tamanho;
    private String status;
}
