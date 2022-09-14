package br.com.luiz.agendamentoservice.response;

import lombok.Data;

@Data
public class TransportadoraResponse {

    private String codigo;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
}
