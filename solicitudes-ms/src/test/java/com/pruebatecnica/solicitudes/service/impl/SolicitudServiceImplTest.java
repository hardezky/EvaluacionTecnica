package com.pruebatecnica.solicitudes.service.impl;

import com.pruebatecnica.solicitudes.dto.SolicitudDTO;
import com.pruebatecnica.solicitudes.dto.DecisionDTO;
import com.pruebatecnica.solicitudes.entity.Cliente;
import com.pruebatecnica.solicitudes.entity.Empleado;
import com.pruebatecnica.solicitudes.entity.Sucursal;
import com.pruebatecnica.solicitudes.repository.ClienteRepository;
import com.pruebatecnica.solicitudes.repository.EmpleadoRepository;
import com.pruebatecnica.solicitudes.repository.SolicitudRepository;
import com.pruebatecnica.solicitudes.repository.SucursalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class SolicitudServiceImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SolicitudServiceImpl solicitudService;

    private Cliente cliente;
    private Sucursal sucursal;

    private Empleado empleado;

    @BeforeEach
    void setupDatos() {
        // Crear sucursal
        sucursal = new Sucursal();
        sucursal.setNombre("Sucursal Test");
        sucursal.setDireccion("Calle Falsa 123");
        //sucursal = sucursalRepository.save(sucursal);
        sucursal = sucursalRepository.saveAndFlush(sucursal);

        // Crear empleado autenticado
        empleado = new Empleado();
        empleado.setUsuario("empleado_test");
        empleado.setPassword("1234");
        empleado.setNombre("Empleado Prueba");
        empleado.setSucursal(sucursal);
        //empleadoRepository.save(empleado);
        empleadoRepository.saveAndFlush(empleado);

        // Crear cliente de prueba
        cliente = new Cliente();
        cliente.setNombre("Juan X");
        //clienteRepository.save(cliente);
        clienteRepository.saveAndFlush(cliente);

        // Simular usuario autenticado
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("empleado_test", null, null)
        );
    }

    private SolicitudDTO crearSolicitud(BigDecimal monto, int plazo, BigDecimal ingresos) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setMonto(monto);
        dto.setPlazoMeses(plazo);
        dto.setIngresosMensuales(ingresos);
        dto.setClienteId(cliente.getId());
        dto.setSucursalId(sucursal.getId());
        dto.setEmpleadoId(empleado.getId());
        return dto;
    }



    // 1. Caso aprobado — monto bajo, ingresos altos
    @Test
    void debeAprobarMontoBajoIngresosAltos() {
        System.out.println("\n [TEST] Caso 1: Monto bajo / ingresos altos → Esperado: APROBADO");

        SolicitudDTO dto = crearSolicitud(BigDecimal.valueOf(20000), 12, BigDecimal.valueOf(10000));

        DecisionDTO result = solicitudService.procesarSolicitud(dto);

        assertNotNull(result);
        assertEquals("APROBADO", result.getDecision());
        System.out.printf("   Resultado → %s | Motivo: %s%n", result.getDecision(), result.getMotivo());
    }

    // 2. Caso rechazado — monto alto, ingresos bajos
    @Test
    void debeRechazarMontoAltoIngresosBajos() {

        System.out.println("\n [TEST] Caso 2: Monto alto / ingresos bajos → Esperado: RECHAZADO");

        SolicitudDTO dto = crearSolicitud(BigDecimal.valueOf(300000), 48, BigDecimal.valueOf(10000));

        DecisionDTO result = solicitudService.procesarSolicitud(dto);

        assertNotNull(result);
        assertEquals("RECHAZADO", result.getDecision());

        System.out.printf("   Resultado → %s | Motivo: %s%n", result.getDecision(), result.getMotivo());

    }

    // 3. Caso rechazado — ingresos nulos
    @Test
    void debeRechazarSiIngresosSonNulos() {
        System.out.println("\n [TEST] Caso 3: Ingresos nulos → Esperado: RECHAZADO");

        SolicitudDTO dto = crearSolicitud(BigDecimal.valueOf(50000), 24, BigDecimal.valueOf(0));

        DecisionDTO result = solicitudService.procesarSolicitud(dto);

        assertNotNull(result);
        assertEquals("RECHAZADO", result.getDecision());
        System.out.printf("   Resultado → %s | Motivo: %s%n", result.getDecision(), result.getMotivo());
    }

    // 4. Caso aprobado — monto medio, ingresos razonables
    @Test
    void debeAprobarMontoMedioIngresosRazonables() {
        System.out.println("\n [TEST] Caso 4: Monto medio / ingresos razonables → Esperado: APROBADO");

        SolicitudDTO dto = crearSolicitud(BigDecimal.valueOf(100000), 24, BigDecimal.valueOf(50000));

        DecisionDTO result = solicitudService.procesarSolicitud(dto);

        assertNotNull(result);
        assertEquals("APROBADO", result.getDecision());
        System.out.printf("   Resultado → %s | Motivo: %s%n", result.getDecision(), result.getMotivo());
    }

    // 5. Caso rechazado — monto alto, plazo largo, ingresos moderados
    @Test
    void debeRechazarMontoAltoPlazoLargo() {
        System.out.println("\n [TEST] Caso 5: Monto alto / plazo largo / ingresos moderados → Esperado: RECHAZADO");

        SolicitudDTO dto = crearSolicitud(BigDecimal.valueOf(400000), 60, BigDecimal.valueOf(30000));

        DecisionDTO result = solicitudService.procesarSolicitud(dto);

        assertNotNull(result);
        assertEquals("RECHAZADO", result.getDecision());
        System.out.printf("   Resultado → %s | Motivo: %s%n", result.getDecision(), result.getMotivo());
    }

}
