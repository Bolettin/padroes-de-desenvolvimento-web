package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PagamentoRepositorioTests {

    @Autowired
    private PagamentoRepositorio repository;

    @Autowired
    private PedidoRepositorio pedidoRepository;

    @Autowired
    private ClienteRepositorio clienteRepository;

    private Pedido criarPedido() {
        Cliente cliente = new Cliente();

        cliente.setNome("João Silva");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("14999999999");

        cliente = clienteRepository.save(cliente);

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        return pedidoRepository.save(pedido);
    }

    @Test
    void deveInserir() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("500.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("CARTAO");
        pagamento.setPedido(pedido);

        Pagamento salvo = repository.save(pagamento);

        assertNotNull(salvo.getId());
        assertEquals(new BigDecimal("500.00"), salvo.getValor());
        assertEquals("APROVADO", salvo.getStatus());
        assertEquals("CARTAO", salvo.getTipo());
        assertEquals(pedido.getId(), salvo.getPedido().getId());
    }

    @Test
    void deveBuscarPorId() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("300.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = repository.save(pagamento);

        Pagamento encontrado =
                repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals(new BigDecimal("300.00"), encontrado.getValor());
        assertEquals("APROVADO", encontrado.getStatus());
        assertEquals("PIX", encontrado.getTipo());
        assertEquals(pedido.getId(), encontrado.getPedido().getId());
    }

    @Test
    void deveListar() {
        Pedido pedido1 = criarPedido();
        Pedido pedido2 = criarPedido();

        Pagamento pagamento1 = new Pagamento();
        pagamento1.setValor(new BigDecimal("100.00"));
        pagamento1.setData(LocalDateTime.now());
        pagamento1.setStatus("APROVADO");
        pagamento1.setTipo("PIX");
        pagamento1.setPedido(pedido1);

        Pagamento pagamento2 = new Pagamento();
        pagamento2.setValor(new BigDecimal("200.00"));
        pagamento2.setData(LocalDateTime.now());
        pagamento2.setStatus("PENDENTE");
        pagamento2.setTipo("CARTAO");
        pagamento2.setPedido(pedido2);

        repository.save(pagamento1);
        repository.save(pagamento2);

        var pagamentos = repository.findAll();

        assertTrue(pagamentos.size() >= 2);
        assertTrue(pagamentos.stream()
                .anyMatch(p -> p.getTipo().equals("PIX")));
        assertTrue(pagamentos.stream()
                .anyMatch(p -> p.getTipo().equals("CARTAO")));
    }

    @Test
    void deveAlterar() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("500.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("PENDENTE");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = repository.save(pagamento);

        Integer id = salvo.getId();

        salvo.setStatus("APROVADO");
        salvo.setTipo("CARTAO");

        repository.save(salvo);

        Pagamento alterado =
                repository.findById(id).orElse(null);

        assertNotNull(alterado);
        assertEquals(id, alterado.getId());
        assertEquals("APROVADO", alterado.getStatus());
        assertEquals("CARTAO", alterado.getTipo());
    }

    @Test
    void deveExcluir() {
        Pedido pedido = criarPedido();

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("150.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        Pagamento salvo = repository.save(pagamento);

        Integer id = salvo.getId();

        assertTrue(repository.findById(id).isPresent());

        repository.deleteById(id);

        assertFalse(repository.findById(id).isPresent());
    }
}
