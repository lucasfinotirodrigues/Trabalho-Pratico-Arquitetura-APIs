package application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.record.CursoDTO;
import application.model.Curso;
import application.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "Endpoints para gerenciamento de cursos")
@SecurityRequirement(name = "bearerAuth")
public class CursoController {

    private final CursoService cursoService;
    
    @GetMapping
    @Operation(summary = "Listar todos os cursos", description = "Retorna uma lista com todos os cursos cadastrados")
    public ResponseEntity<List<Curso>> listarTodos() {
        List<Curso> cursos = cursoService.listarTodos();
        return ResponseEntity.ok(cursos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Retorna um curso específico pelo seu ID")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo curso", description = "Cria um novo curso no sistema")
    public ResponseEntity<Curso> criar(@Valid @RequestBody CursoDTO curso) {
        Curso curso = new Curso();
        curso.setNome(curso.getNome());
        curso.setDescricao(curso.getDescricao());
        curso.setCargaHoraria(curso.getCargaHoraria());
        
        Curso novoCurso = cursoService.salvar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza os dados de um curso existente")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @Valid @RequestBody CursoDTO curso) {
        Curso curso = new Curso();
        curso.setNome(curso.getNome());
        curso.setDescricao(curso.getDescricao());
        curso.setCargaHoraria(curso.getCargaHoraria());
        
        Curso cursoAtualizado = cursoService.atualizar(id, curso);
        return ResponseEntity.ok(cursoAtualizado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir curso", description = "Remove um curso do sistema")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
