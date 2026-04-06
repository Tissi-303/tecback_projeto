package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.AvaliacaoDTO;
import br.uniesp.si.techback.mapper.AvaliacaoMapper;
import br.uniesp.si.techback.model.Avaliacao;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.AvaliacaoRepository;
import br.uniesp.si.techback.repository.FilmeRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;
    private final AvaliacaoMapper mapper;

    public List<AvaliacaoDTO> listarPorFilme(Long filmeId) {
        log.info("Buscando avaliações do filme ID: {}", filmeId);
        return repository.findByFilmeId(filmeId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public AvaliacaoDTO salvar(AvaliacaoDTO dto) {
        log.info("Salvando avaliação para o filme ID: {}", dto.getFilmeId());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Filme filme = filmeRepository.findById(dto.getFilmeId())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        Avaliacao avaliacao = mapper.toEntity(dto, usuario, filme);
        avaliacao = repository.save(avaliacao);

        return mapper.toDto(avaliacao);
    }

    public void excluir(Long id) {
        log.info("Excluindo avaliação ID: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Avaliação não encontrada");
        }
        repository.deleteById(id);
    }
}