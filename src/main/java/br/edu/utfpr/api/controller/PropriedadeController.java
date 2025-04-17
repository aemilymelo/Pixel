package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.Propriedade;
import br.edu.utfpr.api.repository.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    // Criar nova propriedade
    @PostMapping
    public Propriedade create(@RequestBody Propriedade propriedade) {
        return propriedadeRepository.save(propriedade);
    }

    // Listar todas
    @GetMapping
    public List<Propriedade> getAll() {
        return propriedadeRepository.findAll();
    }
}
