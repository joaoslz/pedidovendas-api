package edu.ifma.dcomp.topicos2.apipedidovendas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    @Positive
    private Integer quantidade;

    @DecimalMin(value = "0.01")
    private BigDecimal preco;

    @DecimalMin(value = "0.00")
    private BigDecimal desconto;

    public Produto getProduto() {
        return id.getProduto();
    }

    //método de delegação
    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    //método de delegação
    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    // método de delegação
    public void setProduto(Produto produto) {
        id.setProduto(produto );
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

// explicar por que não precisamos deste método public
// void setValor(BigDecimal preco)

    @PrePersist
    private void prePersist() {
        this.preco = id.getProduto().getPrecoAtual();
    }

    @Transient
    public BigDecimal getSubTotal() {
        return preco
                .multiply(new BigDecimal(quantidade))
                .subtract(desconto );
    }

    //Méotodo de delegação
    public void baixaEstoque(Integer quantidade) {
        this.getProduto().baixaEstoque(quantidade );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
