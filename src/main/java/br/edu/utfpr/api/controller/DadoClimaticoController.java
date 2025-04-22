package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.DadoClimatico;
import br.edu.utfpr.api.repository.DadoClimaticoRepository;
import br.edu.utfpr.api.service.DadoClimaticoService;
import br.edu.utfpr.api.utils.ViewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/dados-climaticos")
public class DadoClimaticoController extends ViewImpl<DadoClimatico, Long> {
    
    public DadoClimaticoController(DadoClimaticoService service) {
        super(service);
    }

    @Autowired
    private DadoClimaticoRepository repository;

    // ✅ Buscar por estação
    @GetMapping("/por-estacao/{id}")
    public List<DadoClimatico> getByEstacao(@PathVariable Long id) {
        return repository.findByEstacaoId(id);
    }

    // ✅ Buscar por tipo
    @GetMapping("/por-tipo")
    public List<DadoClimatico> getByTipo(@RequestParam String tipo) {
        return repository.findByTipoIgnoreCase(tipo);
    }

    // ✅ Buscar por período
    @GetMapping("/por-periodo")
    public List<DadoClimatico> getByPeriodo(
            @RequestParam String inicio,
            @RequestParam String fim) {
        LocalDateTime dtInicio = LocalDateTime.parse(inicio);
        LocalDateTime dtFim = LocalDateTime.parse(fim);
        return repository.findByDataHoraBetween(dtInicio, dtFim);
    }
}
