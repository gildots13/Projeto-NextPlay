package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.model.Usuario;
import br.com.nextplay.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nextplay.backend.dto.LoginRequestDTO;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario novoUsuario) {
    Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(novoUsuario.getEmail());
    if (usuarioExistente.isPresent()) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Este email já está em uso.");
    }
    usuarioRepository.save(novoUsuario);
    return ResponseEntity.ok("Usuário cadastrado com sucesso!");
}

    @PostMapping("/login")
        public ResponseEntity<?> loginUsuario(@RequestBody LoginRequestDTO loginRequest) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
        }

        Usuario usuario = usuarioOptional.get();

        if (loginRequest.getSenha().equals(usuario.getSenha())) {
        return ResponseEntity.ok(java.util.Map.of(
            "message", "Login bem-sucedido!",
            "usuarioId", usuario.getId()
        ));
        } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos.");
        }
    }
}
