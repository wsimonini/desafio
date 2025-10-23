package io.github.wesleysimonini.authapi.dtos;

import io.github.wesleysimonini.authapi.enums.RoleEnum;

public record UsuarioDto(
        String nome,
        String login,
        String senha,
        RoleEnum role
) {
}
