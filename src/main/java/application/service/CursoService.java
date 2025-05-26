package application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import application.model.Curso;
import application.repository.CursoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }
    
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID: " + id));
    }
    
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }
    
    public Curso atualizar(Long id, Curso cursoAtualizado) {
        Optional<Curso> cursoExistente = cursoRepository.findById(id);
        
        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get();
            curso.setNome(cursoAtualizado.getNome());
            curso.setDescricao(cursoAtualizado.getDescricao());
            curso.setCargaHoraria(cursoAtualizado.getCargaHoraria());
            return cursoRepository.save(curso);
        } else {
            throw new EntityNotFoundException("Curso não encontrado com o ID: " + id);
        }
    }
    
    public void excluir(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Curso não encontrado com o ID: " + id);
        }
    }
}
