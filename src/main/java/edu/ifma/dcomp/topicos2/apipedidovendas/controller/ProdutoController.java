package edu.ifma.dcomp.topicos2.apipedidovendas.controller;


import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public List<Produto> listaDeProdutos() {
        return produtoService.todos();
    }


    @PostMapping
    public ResponseEntity<?> cria(@Validated @RequestBody Produto produto) {

        Produto produtoSalvo = produtoService.salva(produto );

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoSalvo.getId() )
                .toUri();

        return  ResponseEntity.created(uri).body(produtoSalvo );

    }



}
