package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
public class ProdutoRepositorioTests {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    private Categoria criarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");
        categoria.setDescricao("Produtos eletronicos");

        return categoriaRepositorio.save(categoria);
    }

    @Test
    public void deveInserir() {
        Categoria categoria = criarCategoria();

        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setDescricao("Notebook para estudos");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("3500.00"));
        produto.setCategoria(categoria);

        Produto salvo = produtoRepositorio.save(produto);

        assertNotNull(salvo.getId());
        assertEquals("Notebook", salvo.getNome());
        assertEquals(new BigDecimal("3500.00"), salvo.getPreco());
        assertEquals(categoria.getId(), salvo.getCategoria().getId());
    }

    @Test
    public void deveBuscarPorId() {
        Categoria categoria = criarCategoria();

        Produto produto = new Produto();
        produto.setNome("Mouse");
        produto.setDescricao("Mouse sem fio");
        produto.setEstoque((short) 20);
        produto.setPreco(new BigDecimal("100.00"));
        produto.setCategoria(categoria);

        Produto salvo = produtoRepositorio.save(produto);

        Produto encontrado = produtoRepositorio.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Mouse", encontrado.getNome());
        assertEquals(categoria.getId(), encontrado.getCategoria().getId());
    }

    @Test
    public void deveListar() {
        Categoria categoria = criarCategoria();

        Produto produto1 = new Produto();
        produto1.setNome("Teclado");
        produto1.setDescricao("Teclado mecanico");
        produto1.setEstoque((short) 10);
        produto1.setPreco(new BigDecimal("250.00"));
        produto1.setCategoria(categoria);

        Produto produto2 = new Produto();
        produto2.setNome("Monitor");
        produto2.setDescricao("Monitor Full HD");
        produto2.setEstoque((short) 5);
        produto2.setPreco(new BigDecimal("900.00"));
        produto2.setCategoria(categoria);

        produtoRepositorio.save(produto1);
        produtoRepositorio.save(produto2);

        var produtos = produtoRepositorio.findAll();

        assertTrue(produtos.size() >= 2);
    }

    @Test
    public void deveAlterar() {
        Categoria categoria = criarCategoria();

        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setDescricao("Notebook basico");
        produto.setEstoque((short) 10);
        produto.setPreco(new BigDecimal("3000.00"));
        produto.setCategoria(categoria);

        Produto salvo = produtoRepositorio.save(produto);
        Integer id = salvo.getId();

        salvo.setNome("Notebook Gamer");
        salvo.setPreco(new BigDecimal("5000.00"));

        produtoRepositorio.save(salvo);

        Produto alterado = produtoRepositorio.findById(id).orElse(null);

        assertNotNull(alterado);
        assertEquals("Notebook Gamer", alterado.getNome());
        assertEquals(new BigDecimal("5000.00"), alterado.getPreco());
    }

    @Test
    public void deveExcluir() {
        Categoria categoria = criarCategoria();

        Produto produto = new Produto();
        produto.setNome("Produto Excluir");
        produto.setDescricao("Produto de teste");
        produto.setEstoque((short) 1);
        produto.setPreco(new BigDecimal("50.00"));
        produto.setCategoria(categoria);

        Produto salvo = produtoRepositorio.save(produto);
        Integer id = salvo.getId();

        produtoRepositorio.deleteById(id);

        assertFalse(produtoRepositorio.findById(id).isPresent());
    }
}