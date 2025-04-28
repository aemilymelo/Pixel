package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.dto.TipoDadoDTO;
import br.edu.utfpr.api.model.TipoDado;
import br.edu.utfpr.api.service.TipoDadoService;
import br.edu.utfpr.api.utils.ViewImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tipodado", produces = "application/json")
public class TipoDadoController extends ViewImpl<TipoDado, Long> {

    public TipoDadoController(TipoDadoService service) {
        super(service);
    }

    @PostMapping("/dto")
    public ResponseEntity<TipoDado> criarTipoDado(@RequestBody @Valid TipoDadoDTO dto) {
        TipoDado tipoDado = dto.toEntity();
        return service.save(tipoDado); // Salvar o TipoDado usando o service
    }
}
