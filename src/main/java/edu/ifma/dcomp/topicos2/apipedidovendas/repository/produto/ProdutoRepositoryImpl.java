package edu.ifma.dcomp.topicos2.apipedidovendas.repository.produto;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.filter.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                                       Root<Produto> produtoRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if ( !StringUtils.isEmpty(filtro.getNome()) ) {
           // where nome like %asdfg%

           predicates.add(cb.like(produtoRoot.get("nome"),
                                 "%" + filtro.getNome() + "%" ));
        }

        if ( filtro.getPrecoDe() != null ) {
            predicates.add( cb.ge( produtoRoot.get("precoAtual"), filtro.getPrecoDe() ));
       }

        if( filtro.getPrecoAte()  != null ) {
            predicates.add( cb.le( produtoRoot.get("precoAtual"), filtro.getPrecoAte() ));
        }

        if (filtro.getAtivo() != null ) {
            predicates.add( cb.equal( produtoRoot.get("ativo"), filtro.getAtivo() ));
        }
        if (filtro.getCategoriaId() != null) {
            // antes faz o join com categorias
            Path<Integer> categoriaPath = produtoRoot.join("categorias").<Integer>get("id");

            // semelhante a clausula "on" com o critério de junção
            predicates.add ( cb.equal(categoriaPath, filtro.getCategoriaId() ) );
        }

       return predicates.toArray(new Predicate[ predicates.size() ] );
    }


    // --------------------------- Com paginação -----------------------------------------

    @Override
    public Page<Produto> filtrar(ProdutoFiltro filtro, Pageable pageable) {

        // Select p From Produto p

        // 1. Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cq)
        // com a tipagem do tipo a ser selecionado (Produto)
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> cq = cb.createQuery(Produto.class );

        // 2. clausula from e joins
        Root<Produto> produtoRoot = cq.from(Produto.class );

        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cb, produtoRoot  );

        // 4. monta a consulta com as restrições de paginação
        TypedQuery<Produto> query = manager.createQuery(cq);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro) );
    }


    private void adicionaRestricoesDePaginacao(TypedQuery<Produto> query, Pageable pageable) {
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

        // 0 a n-1, n - (2n -1), ...
        query.setFirstResult(primeiroObjetoDaPagina );
        query.setMaxResults(totalObjetosPorPagina );

    }

    private Long total(ProdutoFiltro filtro) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Produto> rootProduto = cq.from(Produto.class);

        Predicate[] predicates = criaRestricoes(filtro, cb, rootProduto);
        cq.where(predicates );

        cq.select( cb.count(rootProduto) );

        return manager.createQuery(cq).getSingleResult();
    }

}
