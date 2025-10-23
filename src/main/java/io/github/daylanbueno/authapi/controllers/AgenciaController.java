package io.github.daylanbueno.authapi.controllers;

import io.github.daylanbueno.authapi.dtos.AgenciaDTO;
import io.github.daylanbueno.authapi.models.Agencia;
import org.springframework.web.bind.annotation.*;
import io.github.daylanbueno.authapi.respositories.AgenciaRepository;
import io.github.daylanbueno.authapi.services.AgenciaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/desafio")
public class AgenciaController {

    private final AgenciaService agenciaService;

    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @PostMapping("/cadastrar")
    public Agencia cadastrar(@RequestBody AgenciaDTO dto) {
        Agencia agencia = Agencia.builder()
                .posX(dto.getPosX())
                .posY(dto.getPosY())
                .nome(dto.getNome())
                .build();
        return agenciaService.cadastrarAgencia(agencia);
    }

    @GetMapping("/distancia")
    public List<Map<String, Double>> distancia(@RequestParam double posX, @RequestParam double posY) {
        return agenciaService.consultarDistancias(posX, posY);
    }
}
