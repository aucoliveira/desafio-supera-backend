package br.com.banco.service.exceptions;

public class TransferenciaNaoEncontradaException  extends RuntimeException {

    private static final long serialVersionUID = 1869300553614629710L;

    public TransferenciaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public TransferenciaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
