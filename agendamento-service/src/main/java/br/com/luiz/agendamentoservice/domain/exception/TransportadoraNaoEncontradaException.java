package br.com.luiz.agendamentoservice.domain.exception;

public class TransportadoraNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public TransportadoraNaoEncontradaException(String codigoTransp) {
        super(String.format("Nao exite uma transportadora com o codigo %s", codigoTransp));
    }

}