package com.pruebatecnica.clientes;

import com.pruebatecnica.clientes.entity.Cliente;
import com.pruebatecnica.clientes.repository.ClienteRepository;
import com.pruebatecnica.clientes.service.impl.ClienteServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente1 = new Cliente(1L, "Juan Pérez", "JUAP800101XXX", "juan@test.com", "555-111-2222", null);
        cliente2 = new Cliente(2L, "Ana Gómez", "ANGM850202YYY", "ana@test.com", "555-333-4444", null);
    }

    @Test
    @DisplayName("Buscar cliente existente por ID")
    void buscarClienteExistente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente1));

        Optional<Cliente> result = clienteService.buscarPorId(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Juan Pérez");
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Buscar cliente que no existe devuelve vacío")
    void buscarClienteNoExistente() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Cliente> result = clienteService.buscarPorId(99L);

        assertThat(result).isEmpty();
        verify(clienteRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("📋 Listar todos los clientes devuelve lista completa")
    void listarTodosLosClientes() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> result = clienteService.listar();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNombre()).isEqualTo("Juan Pérez");
        assertThat(result.get(1).getNombre()).isEqualTo("Ana Gómez");
        verify(clienteRepository, times(1)).findAll();
    }
}
