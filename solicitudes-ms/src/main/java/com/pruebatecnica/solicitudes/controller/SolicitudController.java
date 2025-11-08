package com.pruebatecnica.solicitudes.controller;

import com.pruebatecnica.solicitudes.dto.DecisionDTO;
import com.pruebatecnica.solicitudes.dto.SolicitudDTO;
import com.pruebatecnica.solicitudes.dto.SolicitudListDTO;
import com.pruebatecnica.solicitudes.entity.Solicitud;
import com.pruebatecnica.solicitudes.service.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
//@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudController {

    @Autowired
    private ISolicitudService solicitudService;

    @PostMapping("/crear")
    public ResponseEntity<DecisionDTO> crearSolicitud(@RequestBody SolicitudDTO dto) {
        DecisionDTO result = solicitudService.procesarSolicitud(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Solicitud>> listarSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.listar();
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<SolicitudListDTO>> listarTodasSolicitudes() {
        return ResponseEntity.ok(solicitudService.listarSolicitudes());
    }

    @PostMapping("/procesar-lote")
    public ResponseEntity<List<DecisionDTO>> procesarLote(@RequestBody List<SolicitudDTO> solicitudes) {
        System.out.println("Entra a procesar lotes");
        List<DecisionDTO> resultados = solicitudService.procesarLote(solicitudes);
        System.out.println("Termina de procesar lotes");
        return ResponseEntity.ok(resultados);
    }


}