package br.edu.utfpr.api.service;

import org.springframework.stereotype.Service;

import br.edu.utfpr.api.model.Pessoa;
import br.edu.utfpr.api.repository.PessoaRepository;
import br.edu.utfpr.api.utils.CrudServiceImpl;
@Service
public class PessoaService extends CrudServiceImpl<Pessoa, Long> {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository repository) {
        super(repository);
        this.pessoaRepository = repository;
    }

    public Pessoa findByEmail(String email) {
        return pessoaRepository.findByEmail(email);
    }


}
