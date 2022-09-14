package br.com.luiz.conteinerservice.domain.exception;

public class ConteinerNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public ConteinerNaoEncontradoException(String codigoConteiner) {
        super(String.format("Nao exite um conteiner com o codigo %s", codigoConteiner));
    }

}