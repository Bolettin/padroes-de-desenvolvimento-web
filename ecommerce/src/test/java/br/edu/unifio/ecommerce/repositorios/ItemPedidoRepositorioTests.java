package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.ItemPedido;
import br.edu.unifio.ecommerce.entidades.Pedido;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    private Pedido criarPedido() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joao Silva");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("14999999999");

        cliente = clienteRepositorio.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        return pedidoRepositorio.save(pedido);
    }

    private Produto criarProduto() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");
        categoria.setDescricao("Produtos eletronicos");

        categoria = categoriaRepositorio.save(categoria);

        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setDescricao("Notebook para estudos");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("3500.00"));
        produto.setCategoria(categoria);

        return produtoRepositorio.save(produto);
    }

    @Test
    public void deveInserir() {
        Pedido pedido = criarPedido();
        Produto produto = criarProduto();

        ItemPedido item = new ItemPedido();
        item.setQuantidade(2);
        item.setValorUnitario(new BigDecimal("3500.00"));
        item.setPedido(pedido);
        item.setProduto(produto);

        ItemPedido salvo = itemPedidoRepositorio.save(item);

        assertNotNull(salvo.getId());
        assertEquals(2, salvo.getQuantidade());
        assertEquals(pedido.getId(), salvo.getPedido().getId());
        assertEquals(produto.getId(), salvo.getProduto().getId());
    }

    @Test
    public void deveBuscarPorId() {
        Pedido pedido = criarPedido();
        Produto produto = criarProduto();

        ItemPedido item = new ItemPedido();
        item.setQuantidade(3);
        item.setValorUnitario(new BigDecimal("100.00"));
        item.setPedido(pedido);
        item.setProduto(produto);

        ItemPedido salvo = itemPedidoRepositorio.save(item);

        ItemPedido encontrado = itemPedidoRepositorio.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals(3, encontrado.getQuantidade());
        assertEquals(pedido.getId(), encontrado.getPedido().getId());
        assertEquals(produto.getId(), encontrado.getProduto().getId());
    }

    @Test
    public void deveListar() {
        Pedido pedido = criarPedido();
        Produto produto = criarProduto();

        ItemPedido item1 = new ItemPedido();
        item1.setQuantidade(1);
        item1.setValorUnitario(new BigDecimal("100.00"));
        item1.setPedido(pedido);
        item1.setProduto(produto);

        ItemPedido item2 = new ItemPedido();
        item2.setQuantidade(2);
        item2.setValorUnitario(new BigDecimal("200.00"));
        item2.setPedido(pedido);
        item2.setProduto(produto);

        itemPedidoRepositorio.save(item1);
        itemPedidoRepositorio.save(item2);

        var itens = itemPedidoRepositorio.findAll();

        assertTrue(itens.size() >= 2);
    }

    @Test
    public void deveAlterar() {
        Pedido pedido = criarPedido();
        Produto produto = criarProduto();

        ItemPedido item = new ItemPedido();
        item.setQuantidade(1);
        item.setValorUnitario(new BigDecimal("100.00"));
        item.setPedido(pedido);
        item.setProduto(produto);

        ItemPedido salvo = itemPedidoRepositorio.save(item);
        Integer id = salvo.getId();

        salvo.setQuantidade(5);
        salvo.setValorUnitario(new BigDecimal("150.00"));

        itemPedidoRepositorio.save(salvo);

        ItemPedido alterado = itemPedidoRepositorio.findById(id).orElse(null);

        assertNotNull(alterado);
        assertEquals(5, alterado.getQuantidade());
        assertEquals(new BigDecimal("150.00"), alterado.getValorUnitario());
    }

    @Test
    public void deveExcluir() {
        Pedido pedido = criarPedido();
        Produto produto = criarProduto();

        ItemPedido item = new ItemPedido();
        item.setQuantidade(1);
        item.setValorUnitario(new BigDecimal("100.00"));
        item.setPedido(pedido);
        item.setProduto(produto);

        ItemPedido salvo = itemPedidoRepositorio.save(item);
        Integer id = salvo.getId();

        itemPedidoRepositorio.deleteById(id);

        assertFalse(itemPedidoRepositorio.findById(id).isPresent());
    }
}