package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaController {

   @Autowired
   private PessoaRepository pessoaRepository;

   // Criar nova pessoa (POST)
   @PostMapping
   public ResponseEntity<Pessoa> create(@RequestBody Pessoa p) {
      Pessoa nova = pessoaRepository.save(p);
      return ResponseEntity.status(201).body(nova); // HTTP 201 Created
   }

   // Listar todas com paginação (GET)
   @GetMapping
   public Page<Pessoa> listar(Pageable pageable) {
      return pessoaRepository.findAll(pageable);
   }

   // Buscar por ID (GET)
   @GetMapping("/{id}")
   public ResponseEntity<Pessoa> getById(@PathVariable Long id) {
      return pessoaRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); // HTTP 404 se não encontrar
   }

   // Atualizar pessoa (PUT)
   @PutMapping("/{id}")
   public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
      return pessoaRepository.findById(id).map(pessoa -> {
         pessoa.setNome(novaPessoa.getNome());
         pessoa.setEmail(novaPessoa.getEmail());
         Pessoa atualizada = pessoaRepository.save(pessoa);
         return ResponseEntity.ok(atualizada);
      }).orElse(ResponseEntity.notFound().build());
   }

   // Deletar pessoa (DELETE)
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      return pessoaRepository.findById(id).<ResponseEntity<Void>>map(pessoa -> {
         pessoaRepository.delete(pessoa);
         return ResponseEntity.noContent().build(); // HTTP 204
      }).orElse(ResponseEntity.notFound().build());
   }

}
