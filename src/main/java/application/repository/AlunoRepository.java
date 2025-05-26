package application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByEmail(String email);
    Boolean existsByEmail(String email);
}
