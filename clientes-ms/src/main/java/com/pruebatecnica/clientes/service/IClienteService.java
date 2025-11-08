package com.pruebatecnica.clientes.service;

import com.pruebatecnica.clientes.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    Cliente guardar(Cliente cliente);
    List<Cliente> listar();
    Optional<Cliente> buscarPorId(Long id);

    Cliente buscarPorNombre(String nombre);

    Cliente buscarPorRfc(String RFC);
    void eliminar(Long id);
}
