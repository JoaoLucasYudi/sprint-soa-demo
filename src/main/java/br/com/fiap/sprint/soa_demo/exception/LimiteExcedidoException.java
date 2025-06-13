package br.com.fiap.sprint.soa_demo.exception;

public class LimiteExcedidoException extends RuntimeException {
    public LimiteExcedidoException(String message) {
        super(message);
    }
}