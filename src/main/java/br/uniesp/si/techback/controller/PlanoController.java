// PlanoController.java
package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    @Autowired
    private PlanoRepository repository;

    @GetMapping
    public List<Plano> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Plano> buscarPorCodigo(@PathVariable String codigo) {
        return repository.findByCodigo(codigo.toUpperCase())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}