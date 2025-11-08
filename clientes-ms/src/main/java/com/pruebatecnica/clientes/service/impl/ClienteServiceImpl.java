package com.pruebatecnica.clientes.service.impl;

import com.pruebatecnica.clientes.service.IClienteService;
import com.pruebatecnica.clientes.entity.Cliente;
import com.pruebatecnica.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }


    public Cliente buscarPorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente buscarPorRfc(String rfc) {
        return clienteRepository.findByRfc(rfc)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
