package com.pruebatecnica.auth.security;

import com.pruebatecnica.auth.entity.Empleado;
import com.pruebatecnica.auth.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return User.builder()
                .username(empleado.getUsuario())
                .password(empleado.getPassword())
                .roles(empleado.getRol()) // ADMIN o USER
                .build();
    }
}
