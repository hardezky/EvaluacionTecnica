package com.pruebatecnica.solicitudes.service;

import com.pruebatecnica.solicitudes.entity.Sucursal;
import java.util.List;
import java.util.Optional;

public interface ISucursalService {
    Sucursal guardar(Sucursal sucursal);
    List<Sucursal> listar();
    Optional<Sucursal> findById(Long id);
}
