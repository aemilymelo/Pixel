// Source code is decompiled from a .class file using FernFlower decompiler.
package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
   value = {"/pessoa"},
   produces = {"application/json"}
)
public class PessoaController {
   @Autowired
   private PessoaRepository pessoaRepository;

   public PessoaController() {
   }

   @GetMapping({"/1"})
   public Pessoa getOne() {
      Pessoa p = new Pessoa(1L, "Pedro", "p@uol.com");
      return p;
   }

   @PostMapping({"", "/"})
   public Pessoa create(@RequestBody Pessoa p) {
      throw new Error("Unresolved compilation problem: \n\tCannot make a static reference to the non-static method save(Pessoa) from the type CrudRepository<Pessoa,Long>\n");
   }
}
