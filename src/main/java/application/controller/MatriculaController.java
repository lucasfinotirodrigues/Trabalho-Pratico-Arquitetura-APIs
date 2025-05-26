package application.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Curso;
import application.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
@Tag(name = "Matrículas", description = "Endpoints para gerenciamento de matrículas")
@SecurityRequirement(name = "bearerAuth")
public class MatriculaController {

    private final AlunoService alunoService;
    
    @PostMapping("/{cursoId}")
    @Operation(summary = "Matricular em curso", description = "Matricula o aluno autenticado em um curso")
    public ResponseEntity<String> matricular(@PathVariable Long cursoId) {
        alunoService.matricularEmCurso(cursoId);
        return ResponseEntity.ok("Matrícula realizada com sucesso!");
    }
    
    @GetMapping
    @Operation(summary = "Listar cursos matriculados", description = "Lista todos os cursos em que o aluno autenticado está matriculado")
    public ResponseEntity<Set<Curso>> listarCursosMatriculados() {
        Set<Curso> cursos = alunoService.listarCursosMatriculados();
        return ResponseEntity.ok(cursos);
    }
}
