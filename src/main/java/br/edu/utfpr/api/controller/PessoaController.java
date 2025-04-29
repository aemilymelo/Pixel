package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.service.AuthService;
import br.edu.utfpr.api.service.PessoaService;
import br.edu.utfpr.api.utils.ViewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa", produces = "application/json")
public class PessoaController extends ViewImpl<Pessoa, Long> {

   private final PessoaService service;

   @Autowired
   private AuthService authService;

   public PessoaController(PessoaService service) {
      super(service);
        this.service = service;
   }

   @PostMapping("/save")
   public ResponseEntity<Pessoa> salvarUsuario(@RequestBody Pessoa pessoa) {
      Pessoa pes = service.findByEmail(pessoa.getEmail()).orElse(null);
      if(pes != null){
         throw new RuntimeException("Usuário já existe");
      }
      pessoa.setPassword(authService.passwordEncoder(pessoa.getPassword()));
      ResponseEntity<Pessoa> response = super.save(pessoa);
      response.getBody().setPassword(null);
      return response;
   }
   
}
