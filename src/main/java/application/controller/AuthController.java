package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Usuario;
import application.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken tk = new UsernamePasswordAuthenticationToken(
            usuario.getNomeDeUsuario(), usuario.getSenha());
        authManager.authenticate(tk);
        return tokenService.generateToken(usuario);
    }
}