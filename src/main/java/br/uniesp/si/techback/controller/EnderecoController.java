package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.ViaCepResponseDTO;
import br.uniesp.si.techback.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponseDTO> buscarPorCep(@PathVariable String cep) {
        try {
            ViaCepResponseDTO dto = enderecoService.buscarEnderecoPorCep(cep);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}