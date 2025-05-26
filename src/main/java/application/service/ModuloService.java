package application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import application.model.Modulo;
import application.model.Curso;
import application.repository.ModuloRepository;
import application.repository.CursoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final CursoRepository cursoRepository;
    
    public List<Modulo> listarTodos() {
        return moduloRepository.findAll();
    }
    
    public Modulo buscarPorId(Long id) {
        return moduloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com o ID: " + id));
    }
    
    public Modulo salvar(Modulo modulo, Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID: " + cursoId));
        
        modulo.setCurso(curso);
        return moduloRepository.save(modulo);
    }
    
    public Modulo atualizar(Long id, Modulo moduloAtualizado) {
        Optional<Modulo> moduloExistente = moduloRepository.findById(id);
        
        if (moduloExistente.isPresent()) {
            Modulo modulo = moduloExistente.get();
            modulo.setTitulo(moduloAtualizado.getTitulo());
            modulo.setDescricao(moduloAtualizado.getDescricao());
            return moduloRepository.save(modulo);
        } else {
            throw new EntityNotFoundException("Módulo não encontrado com o ID: " + id);
        }
    }
    
    public void excluir(Long id) {
        if (moduloRepository.existsById(id)) {
            moduloRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Módulo não encontrado com o ID: " + id);
        }
    }
}
