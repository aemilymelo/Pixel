package br.edu.utfpr.api.controller;
import br.edu.utfpr.api.model.Estacao;
import br.edu.utfpr.api.service.EstacaoService;
import br.edu.utfpr.api.utils.ViewImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacoes")
public class EstacaoController extends ViewImpl<Estacao, Long> {

    public EstacaoController(EstacaoService service) {
        super(service);
    }

}
