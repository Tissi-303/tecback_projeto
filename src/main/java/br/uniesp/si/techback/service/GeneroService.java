package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.GeneroDTO;
import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository repository;

    public List<GeneroDTO> listar() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public GeneroDTO buscarPorId(Long id) {
        Genero genero = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        return convertToDto(genero);
    }

    public GeneroDTO salvar(GeneroDTO dto) {
        Genero genero = new Genero();
        genero.setNome(dto.getNome());

        genero = repository.save(genero);
        return convertToDto(genero);
    }

    public GeneroDTO atualizar(Long id, GeneroDTO dto) {
        Genero generoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));

        generoExistente.setNome(dto.getNome());
        generoExistente = repository.save(generoExistente);

        return convertToDto(generoExistente);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível excluir: Gênero não encontrado");
        }
        repository.deleteById(id);
    }

    // Método auxiliar para converter Entidade -> DTO
    private GeneroDTO convertToDto(Genero genero) {
        return new GeneroDTO(genero.getId(), genero.getNome());
    }
}