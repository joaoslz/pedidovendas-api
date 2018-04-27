package edu.ifma.dcomp.topicos2.apipedidovendas.service;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Categoria;
import edu.ifma.dcomp.topicos2.apipedidovendas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    Optional<Categoria> buscaPor(String nome) {

        return Optional.ofNullable( categoriaRepository.findByNome(nome ) );
    }


    @Transactional
    public void salva(Categoria categoria ) {

        this.categoriaRepository.save(categoria );
    }



    @Transactional(readOnly = true)
    public List<Categoria> obterTodasCategorias() {

        return categoriaRepository.findAll();
    }
}
