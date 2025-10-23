package io.github.wesleysimonini.authapi.services.impl;


import io.github.wesleysimonini.authapi.models.Agencia;
import io.github.wesleysimonini.authapi.respositories.AgenciaRepository;
import io.github.wesleysimonini.authapi.services.AgenciaService;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AgenciaServiceImpl implements AgenciaService {

    private final AgenciaRepository agenciaRepository;
    private final Cache<String, List<Agencia>> cache;

    public AgenciaServiceImpl(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES) // Expira em 5 min
                .build();
    }

    @Override
    public Agencia cadastrarAgencia(Agencia agencia) {
        Agencia saved = agenciaRepository.save(agencia);
        cache.invalidate("agencias"); // Limpa cache ao cadastrar nova agência
        return saved;
    }

    @Override
    public List<Map<String, Double>> consultarDistancias(double usuarioX, double usuarioY) {
        List<Agencia> agencias = cache.get("agencias", key -> agenciaRepository.findAll());

        // Calcula distância
        return agencias.stream()
                .map(a -> Map.of(
                        a.getNome() != null ? a.getNome() : "AGENCIA_" + a.getId(),
                        Math.sqrt(Math.pow(usuarioX - a.getPosX(), 2) + Math.pow(usuarioY - a.getPosY(), 2))
                ))
                .sorted(Comparator.comparingDouble(m -> m.values().iterator().next()))
                .collect(Collectors.toList());
    }
}