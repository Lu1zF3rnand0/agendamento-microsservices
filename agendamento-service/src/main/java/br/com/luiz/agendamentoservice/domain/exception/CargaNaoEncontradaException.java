package br.com.luiz.agendamentoservice.domain.exception;

public class CargaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CargaNaoEncontradaException(String codigoCarga) {
        super(String.format("Nao exite uma carga com o codigo %s", codigoCarga));
    }

}