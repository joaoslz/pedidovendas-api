package edu.ifma.dcomp.topicos2.apipedidovendas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

class GenericoService<T> {

    private final JpaRepository<T, Integer> repository;

    GenericoService(JpaRepository<T, Integer> repository ) {
        this.repository = repository;
    }

    T salva(T entity) {
        return repository.save(entity);
    }

    public List<T> buscaTodasAsEntities() {
        return repository.findAll();
    }

    T atualiza(Integer id, T entity) {

        T entityManager = this.buscaPor(id );
        BeanUtils.copyProperties(entity, entityManager, "id" );
        this.salva(entityManager );

        return entityManager;
    }


    T buscaPor(Integer id) {
        return repository.findById(id )
                         .orElseThrow(
                                 () ->new EmptyResultDataAccessException(1 )
                         );
    }

}

