package com.pruebatecnica.solicitudes.service;

import com.pruebatecnica.solicitudes.entity.Empleado;
import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {
    Empleado guardar(Empleado empleado);
    List<Empleado> listar();
    Optional<Empleado> buscarPorUsuario(String usuario);

    Optional<Empleado> findById(Long id);

}
