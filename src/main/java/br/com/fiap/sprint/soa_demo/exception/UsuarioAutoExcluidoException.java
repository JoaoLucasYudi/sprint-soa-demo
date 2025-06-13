package br.com.fiap.sprint.soa_demo.exception;

public class UsuarioAutoExcluidoException extends RuntimeException {
    public UsuarioAutoExcluidoException(String message) {
        super(message);
    }
}