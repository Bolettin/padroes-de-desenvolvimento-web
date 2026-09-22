package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PedidoRepositorioTests {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joao Silva");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("14999999999");

        return clienteRepositorio.save(cliente);
    }

    @Test
    public void deveInserir() {
        Cliente cliente = criarCliente();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepositorio.save(pedido);

        assertNotNull(salvo.getId());
        assertEquals("PENDENTE", salvo.getStatus());
        assertEquals(cliente.getId(), salvo.getCliente().getId());
    }

    @Test
    public void deveBuscarPorId() {
        Cliente cliente = criarCliente();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("APROVADO");
        pedido.setValorTotal(new BigDecimal("800.00"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepositorio.save(pedido);

        Pedido encontrado = pedidoRepositorio.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("APROVADO", encontrado.getStatus());
        assertEquals(new BigDecimal("800.00"), encontrado.getValorTotal());
    }

    @Test
    public void deveListar() {
        Cliente cliente1 = criarCliente();
        Cliente cliente2 = criarCliente();

        Pedido pedido1 = new Pedido();
        pedido1.setData(LocalDateTime.now());
        pedido1.setStatus("PENDENTE");
        pedido1.setValorTotal(new BigDecimal("100.00"));
        pedido1.setCliente(cliente1);

        Pedido pedido2 = new Pedido();
        pedido2.setData(LocalDateTime.now());
        pedido2.setStatus("APROVADO");
        pedido2.setValorTotal(new BigDecimal("200.00"));
        pedido2.setCliente(cliente2);

        pedidoRepositorio.save(pedido1);
        pedidoRepositorio.save(pedido2);

        var pedidos = pedidoRepositorio.findAll();

        assertTrue(pedidos.size() >= 2);
    }

    @Test
    public void deveAlterar() {
        Cliente cliente = criarCliente();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepositorio.save(pedido);
        Integer id = salvo.getId();

        salvo.setStatus("APROVADO");
        salvo.setValorTotal(new BigDecimal("600.00"));

        pedidoRepositorio.save(salvo);

        Pedido alterado = pedidoRepositorio.findById(id).orElse(null);

        assertNotNull(alterado);
        assertEquals("APROVADO", alterado.getStatus());
        assertEquals(new BigDecimal("600.00"), alterado.getValorTotal());
    }

    @Test
    public void deveExcluir() {
        Cliente cliente = criarCliente();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("CANCELADO");
        pedido.setValorTotal(new BigDecimal("100.00"));
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepositorio.save(pedido);
        Integer id = salvo.getId();

        pedidoRepositorio.deleteById(id);

        assertFalse(pedidoRepositorio.findById(id).isPresent());
    }
}