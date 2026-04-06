package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.DiretorDTO;
import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.service.DiretorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diretores")
@RequiredArgsConstructor
@Slf4j
public class DiretorController {

    private final DiretorService diretorService;

    @GetMapping
    public List<DiretorDTO> listar() {
        return diretorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiretorDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(diretorService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/filmes")
    public ResponseEntity<List<FilmeDTO>> listarFilmes(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(diretorService.listarFilmesPorDiretor(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DiretorDTO> criar(@Valid @RequestBody DiretorDTO dto) {
        return ResponseEntity.ok(diretorService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiretorDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DiretorDTO dto) {
        try {
            return ResponseEntity.ok(diretorService.atualizar(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            diretorService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}