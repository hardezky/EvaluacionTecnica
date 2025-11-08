package com.pruebatecnica.solicitudes.repository;

import com.pruebatecnica.solicitudes.entity.Autorizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacionRepository extends JpaRepository<Autorizacion, Long> {
}
