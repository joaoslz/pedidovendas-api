package edu.ifma.dcomp.topicos2.apipedidovendas.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nome;

    @DecimalMin(value = "0.01")
    @Column(name = "preco_atual")
    private BigDecimal precoAtual;

    @NotNull
    private Boolean ativo;

    @Transient
    @NotNull @Min(0)
    private Integer quantidadeEstoque;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
               joinColumns = @JoinColumn(name = "produto_id"),
               inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();


    public Produto() {  }

    public Produto(@NotNull String nome, BigDecimal preco) {
        this.nome = nome;
        this.precoAtual = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(BigDecimal precoAtual) {
        this.precoAtual = precoAtual;
    }

    public Boolean getAtivo() {
        return ativo;
    }


    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }


    public List<Categoria> getCategorias() {
        return categorias;
    }


    public void adiciona(Categoria categoria) {
        categorias.add(categoria );
    }

    public void baixaEstoque(Integer quantidade)  {
        int novaQuantidade = this.getQuantidadeEstoque() - quantidade;

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException
                    ("Não há disponibilidade no estoque de "
                     + quantidade + " itens do produto " + this.getNome() + "."
                     + "Temos disponível apenas " + this.quantidadeEstoque + "Itens" );
        }

        this.setQuantidadeEstoque(novaQuantidade );
    }

    public void adicionaEstoque(@Min(1) Integer quantidade) {
        this.setQuantidadeEstoque(this.getQuantidadeEstoque() + quantidade);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId() );
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
