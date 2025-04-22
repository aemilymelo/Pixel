package br.edu.utfpr.api.service;

import org.springframework.stereotype.Service;

import br.edu.utfpr.api.model.Propriedade;
import br.edu.utfpr.api.repository.PropriedadeRepository;
import br.edu.utfpr.api.utils.CrudServiceImpl;

@Service
public class PropriedadeService extends CrudServiceImpl<Propriedade, Long> {

    public PropriedadeService(PropriedadeRepository repository) {
        super(repository);
    }

}
