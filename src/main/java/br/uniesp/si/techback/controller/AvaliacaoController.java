package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.AvaliacaoDTO;
import br.uniesp.si.techback.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping("/filme/{filmeId}")
    public List<AvaliacaoDTO> listarPorFilme(@PathVariable Long filmeId) {
        return avaliacaoService.listarPorFilme(filmeId);
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> criar(@Valid @RequestBody AvaliacaoDTO dto) {
        try {
            AvaliacaoDTO salva = avaliacaoService.salvar(dto);
            return ResponseEntity.ok(salva);
        } catch (Exception e) {
            log.error("Erro ao criar avaliação: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            avaliacaoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}