package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import application.model.Usuario;
import application.record.GenericResponse;
import application.record.UsuarioDTO;
import application.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public GenericResponse insert(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeDeUsuario(dto.nomeDeUsuario());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioRepo.save(usuario);
        return new GenericResponse("Usuario Adicionado");
    }
}

