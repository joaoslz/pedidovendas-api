package edu.ifma.dcomp.topicos2.apipedidovendas.service;


import edu.ifma.dcomp.topicos2.apipedidovendas.model.Categoria;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.CategoriaRepository;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.ProdutoRepository;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.filter.ProdutoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService  {
   private final ProdutoRepository produtoRepository;
   private final CategoriaRepository categoriaRepository;

   private final GenericoService<Produto> genericoService;

   @Autowired
   public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
       this.produtoRepository = produtoRepository;
       this.categoriaRepository = categoriaRepository;

       this.genericoService = new GenericoService<Produto>(produtoRepository);
   }

   @Transactional
    public Produto salva(Produto produto) {
       validaCategorias( produto.getCategorias() );
       return genericoService.salva(produto );
    }

    private void validaCategorias(List<Categoria> categorias) {
        if (categorias !=null && !categorias.isEmpty() ) {

            categorias.forEach(categoria -> {

                Integer id = categoria.getId();

                if (id == null ) {
                    throw new IllegalArgumentException("O id da categoria não pode ser nulo");
                }

                Optional<Categoria> optional = categoriaRepository.findById(id);
                categoria = optional.orElseThrow( () -> new IllegalArgumentException("Categoria Inválida " + id));
            });
        }
    }


    @Transactional(readOnly = true)
    public List<Produto> todos() {
        return genericoService.buscaTodasAsEntities();

    }

    public List<Produto> buscaPor(String nome) {
        return
                produtoRepository.findByNomeContaining(nome )
                .orElse(new ArrayList<>() );
    }



    @Transactional
    public Produto atualiza(Integer id, Produto produto) {
       return genericoService.atualiza(produto, id);
    }

    @Transactional
    public void atualizaAtributoAtivo(Integer id, Boolean ativo) {
       Produto produto = genericoService.buscaPor(id );
       produto.setAtivo(ativo );
       produtoRepository.save(produto );
    }


    public Page<Produto> buscaPaginada(Pageable pageable) {
       return produtoRepository.findAll(pageable );
    }

    public List<Produto> pesquisa(ProdutoFiltro filtro) {

       return produtoRepository.filtrar(filtro );
    }

    public Page<Produto> pesquisa(ProdutoFiltro filtro, Pageable pageable) {
        return produtoRepository.filtrar(filtro, pageable );

    }

    public Produto buscaPor(Integer id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id );

        return optionalProduto
                .orElseThrow( () ->new EmptyResultDataAccessException(1 ) );
    }
}
