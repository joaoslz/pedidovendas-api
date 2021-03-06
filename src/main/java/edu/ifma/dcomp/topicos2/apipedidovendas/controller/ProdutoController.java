package edu.ifma.dcomp.topicos2.apipedidovendas.controller;


import edu.ifma.dcomp.topicos2.apipedidovendas.controller.event.RecursoCriadoEvent;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Produto;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.filter.ProdutoFiltro;
import edu.ifma.dcomp.topicos2.apipedidovendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200" } )
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ApplicationEventPublisher publisher;

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("/todos")
    public Page<Produto> todosProdutos(Pageable pageable  ) {
        return produtoService.buscaPaginada(pageable );
    }


    @GetMapping
    public List<Produto> pesquisar(ProdutoFiltro filtro) {
        return produtoService.pesquisa(filtro);
    }


    @GetMapping("/{id}")
    public Produto buscaPor(@PathVariable Integer id ) {
       return produtoService.buscaPor(id );
    }


    @GetMapping("/paginacao")
    public Page<Produto> pesquisarComPaginacao(ProdutoFiltro filtro, Pageable pageable) {
        return produtoService.pesquisa(filtro, pageable);
    }


    @GetMapping("/buscapornome")
    public List<Produto> buscaPeloNome(@RequestParam String nome ) {
        return produtoService.buscaPor(nome );
    }


    @PostMapping
    public ResponseEntity<?> cria(@Validated @RequestBody Produto produto,
                                  HttpServletResponse response) {

        Produto produtoSalvo = produtoService.salva(produto );

        //vamos usar um evento da aplicação (RecursoCriadoEvent) para adicionar o
        // header_location no cabeçalho da resposta
        publisher.publishEvent( new RecursoCriadoEvent(this, response, produtoSalvo.getId() ));

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoSalvo );
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualiza(@PathVariable Integer id,
                                            @Validated @RequestBody Produto produto ) {
        Produto produtoManager = produtoService.atualiza(id, produto );
        return ResponseEntity.ok(produtoManager );
    }



    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaAtributoAtivo(@PathVariable Integer id, @RequestBody Boolean ativo) {
            produtoService.atualizaAtributoAtivo(id, ativo);
    }


}
