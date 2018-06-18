package edu.ifma.dcomp.topicos2.apipedidovendas.repository.produto;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.filter.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoRepositoryQuery {

    List<Produto> filtrar(ProdutoFiltro filtro);

    Page<Produto> filtrar(ProdutoFiltro filtro, Pageable pageable);
}
