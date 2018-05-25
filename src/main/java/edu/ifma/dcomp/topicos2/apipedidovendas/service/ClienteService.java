package edu.ifma.dcomp.topicos2.apipedidovendas.service;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Cliente;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Optional<Cliente> buscaPor(String nome) {
        return Optional.ofNullable( clienteRepository.findByNome(nome ) );
    }


    public Cliente buscaPor(Integer id) {

        Optional<Cliente> optionalCliente = clienteRepository.findById(id );

        return optionalCliente
                .orElseThrow( () ->new EmptyResultDataAccessException(1 ) );
    }


    @Transactional
    public Cliente salva(Cliente cliente ) {
       return this.clienteRepository.save(cliente );
    }


    @Transactional(readOnly = true)
    public List<Cliente> obterTodosClientes() {
        return clienteRepository.findAll();
    }


    @Transactional
    public void excluir(Integer id) {
        clienteRepository.deleteById(id );
    }


    @Transactional
    public Cliente atualiza(Integer id, Cliente cliente) {
        Cliente clienteManager = this.buscaPor(id );

        BeanUtils.copyProperties(cliente, clienteManager, "id" );
        this.salva(clienteManager );

        return clienteManager;
    }
}
