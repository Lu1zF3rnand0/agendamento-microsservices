package br.com.luiz.transportadoraservice.domain.exception;

public class TransportadoraNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public TransportadoraNaoEncontradoException(String codigoTransp) {
        super(String.format("Nao exite um transportadora com o codigo %s", codigoTransp));
    }

}