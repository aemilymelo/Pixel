package br.edu.utfpr.api.service;
import org.springframework.stereotype.Service;
import br.edu.utfpr.api.model.DadoClimatico;
import br.edu.utfpr.api.repository.DadoClimaticoRepository;
import br.edu.utfpr.api.utils.CrudServiceImpl;

@Service
public class DadoClimaticoService extends CrudServiceImpl<DadoClimatico, Long> {

  public DadoClimaticoService(DadoClimaticoRepository repository) {
        super(repository);
    }



}
