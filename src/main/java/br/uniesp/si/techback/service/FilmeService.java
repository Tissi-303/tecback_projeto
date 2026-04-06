package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.mapper.FilmeMapper;
import br.uniesp.si.techback.model.Diretor;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.DiretorRepository;
import br.uniesp.si.techback.repository.FilmeRepository;
import br.uniesp.si.techback.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmeService {

    private final FilmeRepository repository;
    private final DiretorRepository diretorRepository;
    private final GeneroRepository generoRepository;
    private final FilmeMapper mapper;

    public List<FilmeDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public FilmeDTO salvar(FilmeDTO dto) {
        log.info("Salvando filme: {}", dto.getTitulo());

        Diretor diretor = null;
        if (dto.getDiretorId() != null) {
            diretor = diretorRepository.findById(dto.getDiretorId())
                    .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));
        }

        Genero genero = null;
        if (dto.getGeneroId() != null) {
            genero = generoRepository.findById(dto.getGeneroId())
                    .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        }

        Filme filme = mapper.toEntity(dto, diretor, genero);
        return mapper.toDto(repository.save(filme));
    }

    public FilmeDTO buscarPorId(Long id) {
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        return mapper.toDto(filme);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public FilmeDTO atualizar(Long id, FilmeDTO dto) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Filme não encontrado");
        }
        dto.setId(id);
        return salvar(dto); // Reutiliza a lógica de busca de diretor/gênero do salvar
    }
}