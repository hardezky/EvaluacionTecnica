package com.pruebatecnica.solicitudes.repository;

import com.pruebatecnica.solicitudes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    List<Cliente> findAll();

    Cliente findAllById(Long id);


}
