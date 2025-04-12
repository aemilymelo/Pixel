package br.edu.utfpr.api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositories<T, ID> extends JpaRepository<T, ID>   {

    
}
