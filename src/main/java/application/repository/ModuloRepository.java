package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.model.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    
}
