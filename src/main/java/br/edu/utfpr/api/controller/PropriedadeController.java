package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.model.Propriedade;
import br.edu.utfpr.api.service.PropriedadeService;
import br.edu.utfpr.api.utils.ViewImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController extends ViewImpl<Propriedade, Long> {

    public PropriedadeController(PropriedadeService service) {
        super(service);
    }


}
