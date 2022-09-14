package br.com.luiz.veiculoservice.domain.exception;

public class VeiculoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public VeiculoNaoEncontradoException(String codigoVeiculo) {
        super(String.format("Nao exite um veiculo com o codigo %s", codigoVeiculo));
    }

}