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

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
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