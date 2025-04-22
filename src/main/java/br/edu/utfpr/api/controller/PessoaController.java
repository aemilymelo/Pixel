package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.utils.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaController extends CrudServiceImpl<Pessoa, Long> {
   
   public PessoaController(JpaRepository<Pessoa, Long> repository) {
      super(repository);
   }



}
