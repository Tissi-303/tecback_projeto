package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.GeneroDTO;
import br.uniesp.si.techback.service.GeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
@Slf4j
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> listar() {
        log.info("Listando todos os gêneros");
        return generoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarPorId(@PathVariable Long id) {
        try {
            GeneroDTO genero = generoService.buscarPorId(id);
            log.debug("Gênero encontrado: {}", genero);
            return ResponseEntity.ok(genero);
        } catch (Exception e) {
            log.error("Erro ao buscar gênero com ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> criar(@Valid @RequestBody GeneroDTO generoDTO) {
        log.info("Criando novo gênero: {}", generoDTO.getNome());
        try {
            GeneroDTO generoSalvo = generoService.salvar(generoDTO);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(generoSalvo.getId())
                    .toUri();
            return ResponseEntity.created(location).body(generoSalvo);
        } catch (Exception e) {
            log.error("Erro ao criar gênero: {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody GeneroDTO generoDTO) {
        log.info("Atualizando gênero ID {}: {}", id, generoDTO.getNome());
        try {
            GeneroDTO atualizado = generoService.atualizar(id, generoDTO);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            log.error("Erro ao atualizar gênero ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("Excluindo gênero ID: {}", id);
        try {
            generoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro ao excluir gênero ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}