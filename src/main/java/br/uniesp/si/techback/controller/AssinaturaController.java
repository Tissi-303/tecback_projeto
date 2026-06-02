package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.AssinaturaDTO;
import br.uniesp.si.techback.service.AssinaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @GetMapping
    public ResponseEntity<List<AssinaturaDTO>> listarTodos() {
        return ResponseEntity.ok(assinaturaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssinaturaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assinaturaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AssinaturaDTO> criar(@Valid @RequestBody AssinaturaDTO dto) {
        AssinaturaDTO salva = assinaturaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssinaturaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AssinaturaDTO dto) {
        return ResponseEntity.ok(assinaturaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        assinaturaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}