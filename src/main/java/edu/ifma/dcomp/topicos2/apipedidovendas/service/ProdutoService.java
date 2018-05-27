package edu.ifma.dcomp.topicos2.apipedidovendas.service;


import edu.ifma.dcomp.topicos2.apipedidovendas.model.Categoria;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.CategoriaRepository;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class ProdutoService {

   private final ProdutoRepository produtoRepository;
   private final CategoriaRepository categoriaRepository;

   private final GenericoService<Produto> genericoService;

   @Autowired
   public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
       this.produtoRepository = produtoRepository;
       this.categoriaRepository = categoriaRepository;

       genericoService = new GenericoService<Produto>(produtoRepository );
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

    @Transactional
    public Produto atualiza(Integer id, Produto produto) {
       return genericoService.atualiza(id, produto);
    }

    @Transactional
    public void atualizaAtributoAtivo(Integer id, Boolean ativo) {
       Produto produto = genericoService.buscaPor(id );
       produto.setAtivo(ativo );
       produtoRepository.save(produto );
    }

 /*
    public Produto buscaPor(Integer id) {
       return produtoRepository.findById(id )
                               .orElseThrow( () ->new EmptyResultDataAccessException(1 ) );
    }
*/

}
