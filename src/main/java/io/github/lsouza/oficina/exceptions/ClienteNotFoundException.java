package io.github.lsouza.oficina.exceptions;

public class ClienteNotFoundException extends ResourceNotFoundException {
    public ClienteNotFoundException(String campo, String message) {
        super(campo, message);
    }
}
