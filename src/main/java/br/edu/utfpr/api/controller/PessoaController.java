package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.dto.PessoaDTO;
import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.service.PessoaService;
import br.edu.utfpr.api.utils.ViewImpl;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaController extends ViewImpl<Pessoa, Long> {

   public PessoaController(PessoaService service) {
      super(service);
   }

   @PostMapping("/dto")
   public ResponseEntity<Pessoa> criarPessoa(@RequestBody @Valid PessoaDTO dto) {
      Pessoa pessoa = dto.toEntity();
      return service.save(pessoa);
   }
}
