package com.pruebatecnica.solicitudes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;


    private BigDecimal monto;
    private Integer plazoMeses;
    private BigDecimal ingresosMensuales;
    private String destino;
    private LocalDateTime fechaSolicitud;
    private String estado; // PENDIENTE/APROBADO/RECHAZADO
    private Integer score;
    private String decisionMotivo;


    @ManyToOne
    @JoinColumn(name = "procesado_por")
    private Empleado procesadoPor;

}