package br.edu.unifio.ecommerce.repositorios;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveSalvarUmaCategoriaNova() {
        var categoria = new Categoria();

        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos em geral");

        categoriaRepositorio.save(categoria);

        assertNotNull(categoria.getId());
        assertEquals("Eletrônicos", categoria.getNome());
        assertEquals("Produtos eletrônicos em geral", categoria.getDescricao());
    }

    @Test
    public void deveBuscarCategoriaPorId() {
        var categoria = new Categoria();

        categoria.setNome("Informática");
        categoria.setDescricao("Produtos de informática");

        categoriaRepositorio.save(categoria);

        var categoriaEncontrada =
                categoriaRepositorio.findById(categoria.getId());

        assertTrue(categoriaEncontrada.isPresent());
        assertEquals("Informática", categoriaEncontrada.get().getNome());
        assertEquals("Produtos de informática",
                categoriaEncontrada.get().getDescricao());
    }

    @Test
    public void deveListarCategorias() {
        var categoria1 = new Categoria();
        categoria1.setNome("Eletrônicos");
        categoria1.setDescricao("Produtos eletrônicos");

        var categoria2 = new Categoria();
        categoria2.setNome("Informática");
        categoria2.setDescricao("Produtos de informática");

        categoriaRepositorio.save(categoria1);
        categoriaRepositorio.save(categoria2);

        var categorias = categoriaRepositorio.findAll();

        assertTrue(categorias.size() >= 2);
        assertTrue(categorias.stream()
                .anyMatch(c -> c.getNome().equals("Eletrônicos")));
        assertTrue(categorias.stream()
                .anyMatch(c -> c.getNome().equals("Informática")));
    }

    @Test
    public void deveAlterarUmaCategoria() {
        var categoria = new Categoria();

        categoria.setNome("Eletrônicos");
        categoria.setDescricao("Produtos eletrônicos");

        categoriaRepositorio.save(categoria);

        var id = categoria.getId();

        categoria.setNome("Eletrônicos e Tecnologia");
        categoria.setDescricao("Produtos eletrônicos e tecnológicos");

        categoriaRepositorio.save(categoria);

        var categoriaAlterada =
                categoriaRepositorio.findById(id);

        assertTrue(categoriaAlterada.isPresent());
        assertEquals(id, categoriaAlterada.get().getId());
        assertEquals("Eletrônicos e Tecnologia",
                categoriaAlterada.get().getNome());
        assertEquals("Produtos eletrônicos e tecnológicos",
                categoriaAlterada.get().getDescricao());
    }

    @Test
    public void deveExcluirUmaCategoria() {
        var categoria = new Categoria();

        categoria.setNome("Categoria para excluir");
        categoria.setDescricao("Categoria que será removida");

        categoriaRepositorio.save(categoria);

        var id = categoria.getId();

        assertTrue(categoriaRepositorio.findById(id).isPresent());

        categoriaRepositorio.deleteById(id);

        assertFalse(categoriaRepositorio.findById(id).isPresent());
    }
}

