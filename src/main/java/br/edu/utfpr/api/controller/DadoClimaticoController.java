package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.dto.DadoClimaticoDTO;
import br.edu.utfpr.api.model.DadoClimatico;
import br.edu.utfpr.api.model.Estacao;
import br.edu.utfpr.api.service.DadoClimaticoService;
import br.edu.utfpr.api.service.EstacaoService;
import br.edu.utfpr.api.utils.ViewImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/dados-climaticos")
public class DadoClimaticoController extends ViewImpl<DadoClimatico, Long> {

    private final EstacaoService estacaoService;
    private final DadoClimaticoService dadoClimaticoService;

    public DadoClimaticoController(DadoClimaticoService service, EstacaoService estacaoService) {
        super(service);
        this.estacaoService = estacaoService;
        this.dadoClimaticoService = service;
    }

    // ✅ Criar Dado Climático
    @PostMapping("/dto")
    public ResponseEntity<DadoClimatico> criarDadoClimatico(@RequestBody @Valid DadoClimaticoDTO dto) {
        // Buscar a Estacao pelo ID
        ResponseEntity<Estacao> response = estacaoService.findById(dto.getEstacaoId());
        if (!response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se a Estação não for encontrada
        }

        Estacao estacao = response.getBody();
        DadoClimatico dadoClimatico = dto.toEntity();
        dadoClimatico.setEstacao(estacao);

        return service.save(dadoClimatico);
    }

    // ✅ Buscar por estação
    @GetMapping("/por-estacao/{id}")
    public List<DadoClimatico> getByEstacao(@PathVariable Long id) {
        return dadoClimaticoService.findByEstacaoId(id);
    }

    // ✅ Buscar por tipo
    @GetMapping("/por-tipo")
    public List<DadoClimatico> getByTipo(@RequestParam String tipo) {
        return dadoClimaticoService.findByTipoIgnoreCase(tipo);
    }

    // ✅ Buscar por período
    @GetMapping("/por-periodo")
    public List<DadoClimatico> getByPeriodo(
            @RequestParam String inicio,
            @RequestParam String fim) {
        LocalDateTime dtInicio = LocalDateTime.parse(inicio);
        LocalDateTime dtFim = LocalDateTime.parse(fim);
        return dadoClimaticoService.findByDataHoraBetween(dtInicio, dtFim);
    }
}
