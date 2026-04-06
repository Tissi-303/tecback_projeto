package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.FavoritoDTO;
import br.uniesp.si.techback.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
@Slf4j
public class FavoritoController {

    private final FavoritoService favoritoService;

    @GetMapping("/usuario/{usuarioId}")
    public List<FavoritoDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return favoritoService.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<FavoritoDTO> criar(@RequestBody FavoritoDTO favoritoDTO) {
        try {
            FavoritoDTO salvo = favoritoService.salvar(favoritoDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(salvo.getId())
                    .toUri();
            return ResponseEntity.created(location).body(salvo);
        } catch (Exception e) {
            log.error("Erro ao favoritar: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            favoritoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}