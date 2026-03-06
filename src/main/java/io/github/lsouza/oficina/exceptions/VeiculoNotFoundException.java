package io.github.lsouza.oficina.exceptions;

public class VeiculoNotFoundException extends ResourceNotFoundException {
    public VeiculoNotFoundException(String campo, String message) {
        super(campo, message);
    }
}
