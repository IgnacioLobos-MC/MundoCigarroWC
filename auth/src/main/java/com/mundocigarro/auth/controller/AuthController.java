package com.mundocigarro.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.auth.dto.LoginRequest;
import com.mundocigarro.auth.dto.LoginResponse;
import com.mundocigarro.auth.model.Usuario;
import com.mundocigarro.auth.repository.UsuarioRepository;
import com.mundocigarro.auth.security.JwtProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación",
    description = "Endpoints para gestionar la autenticación de usuarios en Mundo Cigarro"
)
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    @Operation(summary = "Iniciar Sesión",
        description = "Permite a los usuarios iniciar sesión y obtener un token JWT para autenticación"
    )
    
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername()).orElse(null);

        if(usuario == null){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        if(!usuario.getPassword().equals(request.getPassword())){

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Contraseña incorrecta");
        }

        String token =
                jwtProvider.generateToken(
                        usuario.getUsername(),
                        usuario.getRol());
        return ResponseEntity.ok(
                new LoginResponse(token));
    }
}