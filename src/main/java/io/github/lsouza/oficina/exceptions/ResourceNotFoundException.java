package io.github.lsouza.oficina.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String campo;

    public ResourceNotFoundException(String campo, String message) {
        super(message);
        this.campo = campo;
    }
}
