package edu.ifma.dcomp.topicos2.apipedidovendas.service;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmissaoPedidoService {

    @Autowired
    private final PedidoService pedidoService;

    public EmissaoPedidoService(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Transactional
    public Pedido emitir(Pedido pedido) {
        return pedido;
    }


}
