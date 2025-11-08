package com.pruebatecnica.solicitudes.repository;

import com.pruebatecnica.solicitudes.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByUsuario(String usuario);

    Optional<Empleado> findById(Long id);
}
