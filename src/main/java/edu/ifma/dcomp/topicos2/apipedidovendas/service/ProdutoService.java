package edu.ifma.dcomp.topicos2.apipedidovendas.service;


import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

   private final ProdutoRepository produtoRepository;

   @Autowired
   public ProdutoService(ProdutoRepository produtoRepository) {
       this.produtoRepository = produtoRepository;
   }


   @Transactional
    public Produto salva(Produto produto) {
        return produtoRepository.save(produto );
    }


    public List<Produto> todos() {
        return produtoRepository.findAll();

    }
}
