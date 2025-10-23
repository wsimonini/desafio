package io.github.wesleysimonini.authapi.services.impl;


import io.github.wesleysimonini.authapi.models.Agencia;
import io.github.wesleysimonini.authapi.respositories.AgenciaRepository;
import io.github.wesleysimonini.authapi.services.AgenciaService;
import org.springframework.stereotype.Service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AgenciaServiceImpl implements AgenciaService {

    private final AgenciaRepository agenciaRepository;
    private final Cache<String, List<Agencia>> cache;
    private final AtomicInteger accessCount = new AtomicInteger(0);

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
        accessCount.set(0); // Reinicia contagem após atualização
        return saved;
    }

    @Override
    public List<Map<String, Double>> consultarDistancias(double usuarioX, double usuarioY) {
        // Incrementa o contador de acessos
        int currentAccess = accessCount.incrementAndGet();

        // Se já consultou 10 vezes, força renovação do cache
        if (currentAccess >= 10) {
            cache.invalidate("agencias");
            accessCount.set(0);
        }

        // Obtém lista do cache (ou busca novamente no banco)
        List<Agencia> agencias = cache.get("agencias", key -> agenciaRepository.findAll());

        // Calcula distância entre o usuário e cada agência
        return agencias.stream()
                .map(a -> Map.of(
                        a.getNome() != null ? a.getNome() : "AGENCIA_" + a.getId(),
                        Math.sqrt(Math.pow(usuarioX - a.getPosX(), 2) + Math.pow(usuarioY - a.getPosY(), 2))
                ))
                .sorted(Comparator.comparingDouble(m -> m.values().iterator().next()))
                .collect(Collectors.toList());
    }
}