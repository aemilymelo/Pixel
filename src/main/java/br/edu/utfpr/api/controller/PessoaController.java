// Source code is decompiled from a .class file using FernFlower decompiler.
package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/pessoa" }, produces = { "application/json" })
public class PessoaController {
   @Autowired
   private PessoaRepository pessoaRepository;

   public PessoaController() {
   }

   @GetMapping("/{id}")
   public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
      return pessoaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping({ "", "/" })
   public Pessoa create(@RequestBody Pessoa p) {
      return pessoaRepository.save(p);
   }
}
