package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.DiretorDTO;
import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.mapper.DiretorMapper;
import br.uniesp.si.techback.mapper.FilmeMapper;
import br.uniesp.si.techback.model.Diretor;
import br.uniesp.si.techback.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiretorService {

    private final DiretorRepository repository;
    private final DiretorMapper mapper;
    private final FilmeMapper filmeMapper;

    public List<DiretorDTO> listar() {
        log.info("Listando todos os diretores");
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public DiretorDTO buscarPorId(Long id) {
        Diretor diretor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));
        return mapper.toDto(diretor);
    }

    public List<FilmeDTO> listarFilmesPorDiretor(Long id) {
        log.info("Buscando filmes do diretor ID: {}", id);
        Diretor diretor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));

        return diretor.getFilmes().stream()
                .map(filmeMapper::toDto)
                .collect(Collectors.toList());
    }

    public DiretorDTO salvar(DiretorDTO dto) {
        log.info("Salvando novo diretor: {}", dto.getNome());
        Diretor diretor = mapper.toEntity(dto);
        diretor = repository.save(diretor);
        return mapper.toDto(diretor);
    }

    public DiretorDTO atualizar(Long id, DiretorDTO dto) {
        Diretor existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diretor não encontrado"));

        existente.setNome(dto.getNome());
        existente.setNome(dto.getNacionalidade());

        return mapper.toDto(repository.save(existente));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Diretor não encontrado");
        }
        repository.deleteById(id);
    }
}