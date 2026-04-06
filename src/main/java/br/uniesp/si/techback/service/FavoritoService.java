package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.FavoritoDTO;
import br.uniesp.si.techback.mapper.FavoritoMapper;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.FilmeRepository;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritoService {

    private final FavoritoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;
    private final FavoritoMapper mapper; // Injetando o Mapper

    public List<FavoritoDTO> listarPorUsuario(Long usuarioId) {
        log.info("Buscando lista de favoritos para o usuário ID: {}", usuarioId);

        // Verifica se o usuário existe antes de listar
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return repository.findByUsuarioId(usuarioId).stream()
                .map(mapper::toDto) // Usando o mapper para converter a lista
                .collect(Collectors.toList());
    }

    public FavoritoDTO salvar(FavoritoDTO dto) {
        log.info("Tentando adicionar filme ID {} aos favoritos do usuário ID {}",
                dto.getFilmeId(), dto.getUsuarioId());

        // Busca as entidades reais no banco de dados
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para favoritar"));

        Filme filme = filmeRepository.findById(dto.getFilmeId())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado para favoritar"));

        // Converte DTO + Entidades para a nova Entidade Favorito via Mapper
        Favorito favorito = mapper.toEntity(dto, usuario, filme);

        //  Salva no banco
        favorito = repository.save(favorito);
        log.info("Favorito salvo com sucesso! ID gerado: {}", favorito.getId());

        // Retorna o DTO convertido
        return mapper.toDto(favorito);
    }

    public void excluir(Long id) {
        log.info("Solicitação para remover favorito ID: {}", id);

        if (!repository.existsById(id)) {
            log.error("Falha ao excluir: Favorito ID {} não existe", id);
            throw new RuntimeException("Favorito não encontrado");
        }

        repository.deleteById(id);
        log.debug("Favorito ID {} removido do banco de dados", id);
    }

    public FavoritoDTO buscarPorId(Long id) {
        log.info("Buscando detalhe do favorito ID: {}", id);
        Favorito favorito = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
        return mapper.toDto(favorito);
    }
}