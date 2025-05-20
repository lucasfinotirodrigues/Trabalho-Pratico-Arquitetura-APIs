package application.record;

import java.util.Set;

public record CursoDTO(Long id, String nome, String descricao, Set<Long> alunoIds) {
}
