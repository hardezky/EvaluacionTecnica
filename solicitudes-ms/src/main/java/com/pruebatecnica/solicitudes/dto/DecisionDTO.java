package com.pruebatecnica.solicitudes.dto;

import lombok.Data;

@Data
public class DecisionDTO {
    private Long solicitudId;
    private Integer score;
    private String decision;
    private String motivo;
}