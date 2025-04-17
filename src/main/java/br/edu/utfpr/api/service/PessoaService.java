package br.edu.utfpr.api.service;

import org.springframework.stereotype.Service;

import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;
import br.edu.utfpr.api.utils.CrudServiceImpl;

@Service
public class PessoaService extends CrudServiceImpl<Pessoa, Long> {

  public PessoaService(PessoaRepository pessoaRepository) {
        super(pessoaRepository);  // Passando o repositório concreto para o CrudServiceImpl
    }

}
