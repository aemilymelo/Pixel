package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.model.Propriedade;
import br.edu.utfpr.api.utils.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController extends CrudServiceImpl<Propriedade, Long> {

   public PropriedadeController(JpaRepository<Propriedade, Long> repository) {
      super(repository);
   }

}
