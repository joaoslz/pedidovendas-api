package edu.ifma.dcomp.topicos2.apipedidovendas.repository;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Categoria;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.produto.ProdutoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, ProdutoRepositoryQuery {

    Produto findByNome(String nome);

    Optional< List<Produto> > findByNomeContaining(String nome );


}
