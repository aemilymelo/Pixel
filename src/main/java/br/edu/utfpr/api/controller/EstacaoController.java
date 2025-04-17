package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.Estacao;
import br.edu.utfpr.api.repository.EstacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estacoes")
public class EstacaoController {

    @Autowired
    private EstacaoRepository estacaoRepository;

    @PostMapping
    public Estacao create(@RequestBody Estacao estacao) {
        return estacaoRepository.save(estacao);
    }

    @GetMapping
    public List<Estacao> getAll() {
        return estacaoRepository.findAll();
    }
}
