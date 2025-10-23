package io.github.wesleysimonini.authapi.infra.exceptions;

public class UnauthorizedException extends  RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
