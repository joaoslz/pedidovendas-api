package edu.ifma.dcomp.topicos2.apipedidovendas.repository.produto;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.filter.ProdutoFiltro;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Produto> filtrar(ProdutoFiltro filtro) {


        // Select p From Produto p

        // 1. Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cq)
        // com a tipagem do tipo a ser selecionado (Produto)
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> cq = cb.createQuery(Produto.class );

        // 2. clausula from e joins
        Root<Produto> produtoRoot = cq.from(Produto.class );


        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot  );

        // 4. monta a consulta com as restrições
        cq.select(produtoRoot )
          .where(restricoes )
          .orderBy( cb.asc(produtoRoot.get("nome")) );


        // 5. cria e executa a consula
        return manager.createQuery(cq)
                      .getResultList();
    }


    private Predicate[] criaRestricoes(ProdutoFiltro filtro,
                                       CriteriaBuilder cb,
                                       Root<Produto> fromProduto) {
        List<Predicate> predicates = new ArrayList<>();

        if ( !StringUtils.isEmpty(filtro.getNome()) ) {
           predicates.add(cb.like(cb.lower(fromProduto.get("nome")),
                                 "%" + filtro.getNome().toLowerCase() + "%" ));
        }

        if ( filtro.getPrecoDe() != null ) {
            predicates.add( cb.ge( fromProduto.get("preco"), filtro.getPrecoDe() ));
       }

        if( filtro.getPrecoAte()  != null ) {
            predicates.add( cb.le( fromProduto.get("preco"), filtro.getPrecoAte() ));
        }

        if (filtro.getAtivo() != null ) {
            predicates.add( cb.equal( fromProduto.get("ativo"), filtro.getAtivo() ));
        }
        if (filtro.getCategoriaId() != null) {
            // antes faz o join com categorias
            Path<Integer> categoriaPath = fromProduto.join("categorias").<Integer>get("id");

            // semelhante a clausula "on" com o critério de junção
            predicates.add ( cb.equal(categoriaPath, filtro.getCategoriaId() ) );
        }

       return predicates.toArray(new Predicate[ predicates.size() ] );
    }
}
