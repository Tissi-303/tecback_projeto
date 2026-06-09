package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.enums.TipoConteudo;
import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.service.ConteudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

    @Autowired
    private ConteudoService service;

    @GetMapping
    public ResponseEntity<List<Conteudo>> listar(
            @RequestParam(required = false) TipoConteudo tipo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String q) {
        return ResponseEntity.ok(service.listar(tipo, genero, q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conteudo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Conteudo> criar(@RequestBody Conteudo conteudo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(conteudo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conteudo> atualizar(@PathVariable Long id, @RequestBody Conteudo conteudo) {
        return ResponseEntity.ok(service.atualizar(id, conteudo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}