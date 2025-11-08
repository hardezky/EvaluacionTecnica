package com.pruebatecnica.solicitudes.service.impl;

import com.pruebatecnica.solicitudes.entity.Sucursal;
import com.pruebatecnica.solicitudes.repository.SucursalRepository;
import com.pruebatecnica.solicitudes.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements ISucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public Sucursal guardar(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Sucursal> listar() {
        return sucursalRepository.findAll();
    }

    @Override
    public Optional<Sucursal> findById(Long id) {
        return sucursalRepository.findById(id);
    }
}
