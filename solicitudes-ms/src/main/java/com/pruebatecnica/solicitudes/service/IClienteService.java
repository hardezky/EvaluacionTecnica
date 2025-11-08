package com.pruebatecnica.solicitudes.service;

import com.pruebatecnica.solicitudes.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    Cliente guardar(Cliente cliente);
    List<Cliente> listar();
    Optional<Cliente> buscarPorId(Long id);
    void eliminar(Long id);
}
