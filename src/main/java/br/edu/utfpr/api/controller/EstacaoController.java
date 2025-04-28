package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.dto.EstacaoDTO;
import br.edu.utfpr.api.model.Estacao;
import br.edu.utfpr.api.model.Propriedade;
import br.edu.utfpr.api.service.EstacaoService;
import br.edu.utfpr.api.service.PropriedadeService;
import br.edu.utfpr.api.utils.ViewImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/estacao", produces = "application/json")
public class EstacaoController extends ViewImpl<Estacao, Long> {

    private final PropriedadeService propriedadeService;

    public EstacaoController(EstacaoService service, PropriedadeService propriedadeService) {
        super(service);
        this.propriedadeService = propriedadeService;
    }

    @PostMapping("/dto")
    public ResponseEntity<Estacao> criarEstacao(@RequestBody @Valid EstacaoDTO dto) {
        // Buscando a propriedade usando o 'id' em vez de 'propriedadeId'
        ResponseEntity<Propriedade> response = propriedadeService.findById(dto.getId());

        if (!response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(response.getStatusCode()).build();
        }

        Propriedade propriedade = response.getBody();
        Estacao estacao = dto.toEntity();
        estacao.setPropriedade(propriedade); // Associando a propriedade à estação

        return service.save(estacao);
    }
}
