package br.edu.utfpr.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/pessoa")

public class PessoaController {

    @GetMapping("/1")
    @RequestMapping(value ="/pessoa", produces = "application/json")
    public String getOne(){
        return "{'id' : 1, 'nome': 'João' , 'idade': 30}";
    }

}
