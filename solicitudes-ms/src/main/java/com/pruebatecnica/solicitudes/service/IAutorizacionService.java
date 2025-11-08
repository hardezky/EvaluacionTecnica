package com.pruebatecnica.solicitudes.service;

import com.pruebatecnica.solicitudes.entity.Autorizacion;
import java.util.List;
import java.util.Optional;

public interface IAutorizacionService {
    Autorizacion guardar(Autorizacion autorizacion);
    List<Autorizacion> listar();
    Optional<Autorizacion> buscarPorId(Long id);
}
