package edu.ifma.dcomp.topicos2.apipedidovendas.repository.produto;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.filter.ProdutoFiltro;

import java.util.List;

public interface ProdutoRepositoryQuery {

    public List<Produto> filtrar(ProdutoFiltro filtro);

}
