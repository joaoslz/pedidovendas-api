package edu.ifma.dcomp.topicos2.apipedidovendas.controller;

import edu.ifma.dcomp.topicos2.apipedidovendas.model.Categoria;
import edu.ifma.dcomp.topicos2.apipedidovendas.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private  final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {

        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cria(@Validated @RequestBody Categoria categoria, HttpServletResponse response) {

        Categoria categoriaSalva = categoriaService.salva(categoria);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaSalva.getId())
                .toUri();

        //response.setHeader("Location", uri.toString() );

        return  ResponseEntity.created(uri).body(categoriaSalva );
    }

    @GetMapping
    public ResponseEntity<?> listaCategorias() {

        List<Categoria> categorias = categoriaService.obterTodasCategorias();

        if (categorias.isEmpty() ) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok(categorias );

        }

    }

    @GetMapping("/{id}")
    public Categoria buscaPor(@PathVariable Integer id ) {
        return categoriaService.buscaPor(id );
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody void excluir(@PathVariable Integer id) {
        categoriaService.excluir(id );
    }

}
