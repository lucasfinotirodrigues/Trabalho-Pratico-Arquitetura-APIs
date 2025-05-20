package application.record;

import java.util.Set;

public record AlunoDTO(Long id, String nome, String email, Set<Long> cursoIds) {
}
