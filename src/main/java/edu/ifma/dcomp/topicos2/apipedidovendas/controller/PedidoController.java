package edu.ifma.dcomp.topicos2.apipedidovendas.controller;


import edu.ifma.dcomp.topicos2.apipedidovendas.controller.event.RecursoCriadoEvent;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.ItemPedido;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Pedido;
import edu.ifma.dcomp.topicos2.apipedidovendas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private ApplicationEventPublisher publisher;

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @GetMapping
    public List<Pedido> listaDeProdutos() {
        return pedidoService.todos();
    }

    @PostMapping
    public ResponseEntity<?> cria(@Validated @RequestBody Pedido pedido, HttpServletResponse response) {

        Pedido pedidoSalvo = pedidoService.salva(pedido );

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pedidoSalvo.getId() ));

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoSalvo );
    }

    @GetMapping("/{id}")
    public Pedido buscaPor(@PathVariable  Integer id) {
        return pedidoService.buscaPor(id );
    }

    @GetMapping("/{id}/itens")
    public Set<ItemPedido> buscaItensPedido(@PathVariable Integer id) {
        return pedidoService.itensDoPedido(id );
    }
}
