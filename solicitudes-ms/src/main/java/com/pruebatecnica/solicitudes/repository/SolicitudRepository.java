package com.pruebatecnica.solicitudes.repository;

import com.pruebatecnica.solicitudes.dto.SolicitudListDTO;
import com.pruebatecnica.solicitudes.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findAll();
    List<Solicitud> findAllByCliente_Id(Long id);
    @Procedure(procedureName = "sp_calcular_decision")
    String calcularDecision(
            @Param("p_monto") BigDecimal monto,
            @Param("p_plazo") Integer plazo,
            @Param("p_ingresos") BigDecimal ingresos
    );

    @Query("SELECT new com.pruebatecnica.solicitudes.dto.SolicitudListDTO(" +
            "s.id, c.nombre, s.fechaSolicitud, s.monto, s.plazoMeses, s.ingresosMensuales, s.estado) " +
            "FROM Solicitud s JOIN s.cliente c ORDER BY s.fechaSolicitud DESC")
    List<SolicitudListDTO> listarResumen();

}