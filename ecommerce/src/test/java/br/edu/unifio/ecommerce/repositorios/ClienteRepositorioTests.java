package br.edu.unifio.ecommerce.repositorios;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
public class ClienteRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void deveInserir() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joao Silva");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("14999999999");

        Cliente salvo = clienteRepositorio.save(cliente);

        assertNotNull(salvo.getId());
        assertEquals("Joao Silva", salvo.getNome());
        assertEquals("joao@email.com", salvo.getEmail());
    }

    @Test
    public void deveBuscarPorId() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria Souza");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("14988888888");

        Cliente salvo = clienteRepositorio.save(cliente);

        Cliente encontrado = clienteRepositorio.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Maria Souza", encontrado.getNome());
        assertEquals("maria@email.com", encontrado.getEmail());
    }

    @Test
    public void deveListar() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Cliente Um");
        cliente1.setEmail("cliente1@email.com");
        cliente1.setTelefone("14911111111");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Cliente Dois");
        cliente2.setEmail("cliente2@email.com");
        cliente2.setTelefone("14922222222");

        clienteRepositorio.save(cliente1);
        clienteRepositorio.save(cliente2);

        var clientes = clienteRepositorio.findAll();

        assertTrue(clientes.size() >= 2);
    }

    @Test
    public void deveAlterar() {
        Cliente cliente = new Cliente();
        cliente.setNome("Joao");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("14999999999");

        Cliente salvo = clienteRepositorio.save(cliente);
        Integer id = salvo.getId();

        salvo.setNome("Joao da Silva");
        salvo.setEmail("joao.silva@email.com");

        clienteRepositorio.save(salvo);

        Cliente alterado = clienteRepositorio.findById(id).orElse(null);

        assertNotNull(alterado);
        assertEquals("Joao da Silva", alterado.getNome());
        assertEquals("joao.silva@email.com", alterado.getEmail());
    }

    @Test
    public void deveExcluir() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Excluir");
        cliente.setEmail("excluir@email.com");
        cliente.setTelefone("14977777777");

        Cliente salvo = clienteRepositorio.save(cliente);
        Integer id = salvo.getId();

        clienteRepositorio.deleteById(id);

        assertFalse(clienteRepositorio.findById(id).isPresent());
    }
}