package br.edu.unifio.ecommerce;

import java.math.BigDecimal;
import br.edu.unifio.ecommerce.entidades.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

}
