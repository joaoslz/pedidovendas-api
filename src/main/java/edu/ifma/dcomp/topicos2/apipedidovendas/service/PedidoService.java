package edu.ifma.dcomp.topicos2.apipedidovendas.service;


import edu.ifma.dcomp.topicos2.apipedidovendas.model.ItemPedido;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Pagamento;
import edu.ifma.dcomp.topicos2.apipedidovendas.model.Pedido;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final GenericoService<Pedido> genericoService;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository ) {
        this.pedidoRepository = pedidoRepository;
        this.genericoService = new GenericoService<>(pedidoRepository);
    }


    @Transactional
    public Pedido salva(Pedido pedido) {
         return genericoService.salva(pedido );
    }


    @Transactional(readOnly = true)
    public List<Pedido> todos() {
        return genericoService.buscaTodasAsEntities();
    }


    @Transactional
    public Pedido atualiza(Integer id, Pedido pedido) {
        return genericoService.atualiza(pedido, id);
    }

    @Transactional(readOnly = true)
    public Pedido buscaPor(Integer id) {
        return genericoService.buscaPor(id );
    }


    public Set<ItemPedido> itensDoPedido(Integer id) {
        Pedido pedido = buscaPor(id);
        return pedido.getItens();
    }
}
