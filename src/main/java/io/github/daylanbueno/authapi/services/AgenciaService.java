package io.github.daylanbueno.authapi.services;

import io.github.daylanbueno.authapi.models.Agencia;

import java.util.List;
import java.util.Map;

public interface AgenciaService {
    Agencia cadastrarAgencia(Agencia agencia);
    List<Map<String, Double>> consultarDistancias(double usuarioX, double usuarioY);
}