package io.github.wesleysimonini.authapi.services;


import io.github.wesleysimonini.authapi.models.Agencia;

import java.util.List;
import java.util.Map;

public interface AgenciaService {
    Agencia cadastrarAgencia(Agencia agencia);
    List<Map<String, Double>> consultarDistancias(double usuarioX, double usuarioY);
}