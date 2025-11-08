package com.pruebatecnica.clientes.repository;

import com.pruebatecnica.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findByNombre(String nombre);
    Optional<Cliente> findByRfc(String rfc);
}
