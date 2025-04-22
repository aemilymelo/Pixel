package br.edu.utfpr.api.controller;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.api.model.TipoDado;
import br.edu.utfpr.api.utils.CrudServiceImpl;

public class TipoDadoController extends CrudServiceImpl<TipoDado, Long> {
    
    public TipoDadoController(JpaRepository<TipoDado, Long> repository) {
        super(repository);
    }

}
