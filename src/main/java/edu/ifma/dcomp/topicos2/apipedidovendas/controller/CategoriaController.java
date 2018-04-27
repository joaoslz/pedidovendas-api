package edu.ifma.dcomp.topicos2.apipedidovendas.controller;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Categoria;
import edu.ifma.dcomp.topicos2.apipedidovendas.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public List<Categoria> listaCategorias() {

        return categoriaService.obterTodasCategorias();
    }

}
