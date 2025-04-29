package br.edu.utfpr.api.utils;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class ViewImpl<T, ID extends Serializable> implements Crud<T, ID> {

    protected final CrudService<T, ID> service;

    public ViewImpl(CrudService<T, ID> service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<T> save(@RequestBody T entity) {
      return service.save(entity);

    }

    @GetMapping
    @Override
    public List<T> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<T> findById(@PathVariable ID id) {
      return service.findById(id); 
    }
    @DeleteMapping("/{id}")
    @Override
    public  ResponseEntity<Void> deleteById(@PathVariable ID id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<T> update(@PathVariable ID id, T entity) {
        return service.findById(id);
    }
    
}
