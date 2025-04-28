package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.dto.PropriedadeDTO;
import br.edu.utfpr.api.model.Propriedade;
import br.edu.utfpr.api.service.PropriedadeService;
import br.edu.utfpr.api.utils.ViewImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController extends ViewImpl<Propriedade, Long> {

    private final PropriedadeService service;

    public PropriedadeController(PropriedadeService service) {
        super(service);
        this.service = service;
    }

    // Novo endpoint que usa DTO e validação
    @PostMapping("/dto")
    public ResponseEntity<Propriedade> criarPropriedade(@RequestBody @Valid PropriedadeDTO dto) {
        Propriedade propriedade = dto.toEntity();
        return service.save(propriedade);
    }

}
