package application.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import application.model.Aluno;
import application.model.Curso;
import application.repository.AlunoRepository;
import application.repository.CursoRepository;
import application.security.UserDetailsImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));
    }
    
    public Aluno getAlunoAtual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return alunoRepository.findById(userDetails.getId())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
    }
    
    public void matricularEmCurso(Long cursoId) {
        Aluno aluno = getAlunoAtual();
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID: " + cursoId));
        
        aluno.getCursos().add(curso);
        alunoRepository.save(aluno);
    }
    
    public Set<Curso> listarCursosMatriculados() {
        Aluno aluno = getAlunoAtual();
        return aluno.getCursos();
    }
}
