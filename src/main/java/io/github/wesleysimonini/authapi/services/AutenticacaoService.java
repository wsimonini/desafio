package io.github.wesleysimonini.authapi.services;

import io.github.wesleysimonini.authapi.dtos.AuthDto;
import io.github.wesleysimonini.authapi.dtos.TokenResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AutenticacaoService extends UserDetailsService {
    public TokenResponseDto obterToken(AuthDto authDto);
    public String validaTokenJwt(String token);

    TokenResponseDto obterRefreshToken(String s);
}
