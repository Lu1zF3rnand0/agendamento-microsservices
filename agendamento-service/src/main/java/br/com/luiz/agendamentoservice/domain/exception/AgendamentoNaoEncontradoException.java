package br.com.luiz.agendamentoservice.domain.exception;

public class AgendamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public AgendamentoNaoEncontradoException(String codigoAgendamento) {
        super(String.format("Nao exite um agendamento com o codigo %s", codigoAgendamento));
    }

}