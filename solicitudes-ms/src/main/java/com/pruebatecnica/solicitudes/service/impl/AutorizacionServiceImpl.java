package com.pruebatecnica.solicitudes.service.impl;

import com.pruebatecnica.solicitudes.entity.Autorizacion;
import com.pruebatecnica.solicitudes.repository.AutorizacionRepository;
import com.pruebatecnica.solicitudes.service.IAutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorizacionServiceImpl implements IAutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    @Override
    public Autorizacion guardar(Autorizacion autorizacion) {
        return autorizacionRepository.save(autorizacion);
    }

    @Override
    public List<Autorizacion> listar() {
        return autorizacionRepository.findAll();
    }

    @Override
    public Optional<Autorizacion> buscarPorId(Long id) {
        return autorizacionRepository.findById(id);
    }
}
