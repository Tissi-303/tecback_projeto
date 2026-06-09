package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.PlanoDTO;
import br.uniesp.si.techback.mapper.PlanoMapper;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository repository;

    @Autowired
    private PlanoMapper mapper;

    public List<PlanoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PlanoDTO buscarPorId(Long id) {
        Plano plano = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado."));
        return mapper.toDto(plano);
    }

    public PlanoDTO criar(PlanoDTO dto) {
        Plano plano = mapper.toEntity(dto);
        Plano salvo = repository.save(plano);
        return mapper.toDto(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Plano não encontrado.");
        }
        repository.deleteById(id);
    }
}