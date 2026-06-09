package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @PostMapping("/usuario/{usuarioId}/conteudo/{conteudoId}")
    public ResponseEntity<Favorito> adicionar(@PathVariable Long usuarioId, @PathVariable Long conteudoId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionar(usuarioId, conteudoId));
    }

    @DeleteMapping("/usuario/{usuarioId}/conteudo/{conteudoId}")
    public ResponseEntity<Void> remover(@PathVariable Long usuarioId, @PathVariable Long conteudoId) {
        service.remover(usuarioId, conteudoId);
        return ResponseEntity.noContent().build();
    }
}