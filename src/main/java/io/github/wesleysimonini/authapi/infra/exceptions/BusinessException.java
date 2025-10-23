package io.github.wesleysimonini.authapi.infra.exceptions;

public class BusinessException extends  RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
