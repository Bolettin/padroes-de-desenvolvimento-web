package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.ecommerce.entidades.ItemPedido;

@SpringBootTest
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    public void deveSalvarUmItemPedidoNovo() {
        var itemPedido = new ItemPedido();

        var pedido = pedidoRepositorio.findById(1).orElseThrow();
        var produto = produtoRepositorio.findById(1).orElseThrow();

        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("12570.30"));

        itemPedidoRepositorio.save(itemPedido);

        assertNotNull(itemPedido.getId());
        
    }
}
