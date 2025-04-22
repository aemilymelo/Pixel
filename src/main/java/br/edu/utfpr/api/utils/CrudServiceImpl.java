package br.edu.utfpr.api.utils;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



public abstract class CrudServiceImpl<T, ID extends Serializable> implements Crud<T, ID> {
    

    protected final JpaRepository<T, ID> repository;
    
    public CrudServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @PostMapping
    @Override
    public ResponseEntity<T> save(T entity) {
      T nova = repository.save(entity);
      return ResponseEntity.status(201).body(nova);
    }

   @GetMapping
    @Override
    public List<T> findAll(Pageable pageable) {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<T> findById(@PathVariable ID id) {
      return repository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); 
    }
   @DeleteMapping("/{id}")
    @Override
    public  ResponseEntity<Void> deleteById(@PathVariable ID id) {
        return repository.findById(id).<ResponseEntity<Void>>map(entity -> {
            repository.delete(entity);
            return ResponseEntity.noContent().build(); 
         }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<T> update(@PathVariable ID id, T entity) {
        return repository.findById(id).map(existingEntity -> {
            T updatedEntity = repository.save(existingEntity);
            return ResponseEntity.ok(updatedEntity);
        }).orElse(ResponseEntity.notFound().build());
    }
}
