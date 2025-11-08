package com.pruebatecnica.solicitudes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "autorizaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autorizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitud_id")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    private Timestamp fecha = new Timestamp(System.currentTimeMillis());
    private String resultado;
    private String comentarios;
}
