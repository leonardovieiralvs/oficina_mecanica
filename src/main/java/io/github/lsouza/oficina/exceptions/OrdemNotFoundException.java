package io.github.lsouza.oficina.exceptions;

public class OrdemNotFoundException extends ResourceNotFoundException {
    public OrdemNotFoundException(String campo, String message) {
        super(campo, message);
    }
}
