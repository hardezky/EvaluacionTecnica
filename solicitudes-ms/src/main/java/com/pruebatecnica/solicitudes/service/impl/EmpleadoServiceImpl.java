package com.pruebatecnica.solicitudes.service.impl;

import com.pruebatecnica.solicitudes.entity.Empleado;
import com.pruebatecnica.solicitudes.repository.EmpleadoRepository;
import com.pruebatecnica.solicitudes.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorUsuario(String usuario) {
        return empleadoRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }
}
