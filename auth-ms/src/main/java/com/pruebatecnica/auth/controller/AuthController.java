package com.pruebatecnica.auth.controller;

import com.pruebatecnica.auth.dto.JwtResponse;
import com.pruebatecnica.auth.dto.LoginRequest;
import com.pruebatecnica.auth.entity.Empleado;
import com.pruebatecnica.auth.repository.EmpleadoRepository;
import com.pruebatecnica.auth.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {

        Empleado empleado = empleadoRepository.findByUsuario(loginRequest.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), empleado.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(empleado);
        return new JwtResponse(token, "Bearer");

    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        boolean valid = jwtUtil.isTokenValid(token);
        String username = valid ? jwtUtil.extractUsername(token) : null;
        return ResponseEntity.ok(Map.of("valid", valid, "usuario", username));
    }

/*
    @PostMapping("/login2")
    public ResponseEntity<?> login2(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getPassword())
        );

        Empleado empleado = empleadoRepository.findByUsuario(request.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(empleado);
        return ResponseEntity.ok(Map.of(
                "token", token,
                "usuario", empleado.getUsuario(),
                "nombre", empleado.getNombre(),
                "rol", empleado.getRol()
        ));
    }
    */
}