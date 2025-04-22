package br.edu.utfpr.api.controller;

import br.edu.utfpr.api.model.Estacao;
import br.edu.utfpr.api.utils.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacoes")
public class EstacaoController extends CrudServiceImpl<Estacao, Long> {


    public EstacaoController(JpaRepository<Estacao, Long> repository) {
        super(repository);
    }



}
