package br.com.luiz.veiculoservice.domain.exception;

public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String mesage, Throwable cause) {
        super(mesage, cause);
    }
}