package io.github.lsouza.oficina.exceptions;

public class BusinessException extends ResourceNotFoundException {
    public BusinessException(String campo, String message) {
        super(campo, message);
    }
}
