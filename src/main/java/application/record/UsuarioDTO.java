package application.record;

import application.model.Usuario;

public record UsuarioDTO(long id, String nomeDeUsuario, String senha) {
    public UsuarioDTO(Usuario model) {
        this(model.getId(), model.getNomeDeUsuario(), model.getSenha());
    }
}
