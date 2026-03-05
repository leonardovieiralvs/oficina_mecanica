package io.github.lsouza.oficina.exceptions;

import lombok.Getter;

@Getter
public class OperationNotAllowedException extends RuntimeException {

    private String campo;

    public OperationNotAllowedException(String campo, String message) {
        super(message);
        this.campo = campo;
    }
}
