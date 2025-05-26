package application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.dto.ModuloRequest;
import application.service.ModuloService;

import application.model.Modulo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/modulos")
@RequiredArgsConstructor
@Tag(name = "Módulos", description = "Endpoints para gerenciamento de módulos")
@SecurityRequirement(name = "bearerAuth")
public class ModuloController {

    private final ModuloService moduloService;
    
    @GetMapping
    @Operation(summary = "Listar todos os módulos", description = "Retorna uma lista com todos os módulos cadastrados")
    public ResponseEntity<List<Modulo>> listarTodos() {
        List<Modulo> modulos = moduloService.listarTodos();
        return ResponseEntity.ok(modulos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar módulo por ID", description = "Retorna um módulo específico pelo seu ID")
    public ResponseEntity<Modulo> buscarPorId(@PathVariable Long id) {
        Modulo modulo = moduloService.buscarPorId(id);
        return ResponseEntity.ok(modulo);
    }
    
    @PostMapping
    @Operation(summary = "Criar novo módulo", description = "Cria um novo módulo no sistema")
    public ResponseEntity<Modulo> criar(@Valid @RequestBody ModuloRequest moduloRequest) {
        Modulo modulo = new Modulo();
        modulo.setTitulo(moduloRequest.getTitulo());
        modulo.setDescricao(moduloRequest.getDescricao());
        
        Modulo novoModulo = moduloService.salvar(modulo, moduloRequest.getCursoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoModulo);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar módulo", description = "Atualiza os dados de um módulo existente")
    public ResponseEntity<Modulo> atualizar(@PathVariable Long id, @Valid @RequestBody ModuloRequest moduloRequest) {
        Modulo modulo = new Modulo();
        modulo.setTitulo(moduloRequest.getTitulo());
        modulo.setDescricao(moduloRequest.getDescricao());
        
        Modulo moduloAtualizado = moduloService.atualizar(id, modulo);
        return ResponseEntity.ok(moduloAtualizado);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir módulo", description = "Remove um módulo do sistema")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        moduloService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
