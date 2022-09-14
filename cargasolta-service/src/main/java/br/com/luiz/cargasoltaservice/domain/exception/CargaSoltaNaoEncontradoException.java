package br.com.luiz.cargasoltaservice.domain.exception;

public class CargaSoltaNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CargaSoltaNaoEncontradoException(String codigoCarga) {
        super(String.format("Nao exite uma carga solta com o codigo %s", codigoCarga));
    }

}