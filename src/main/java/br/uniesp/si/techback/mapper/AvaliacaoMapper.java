package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.AvaliacaoDTO;
import br.uniesp.si.techback.model.Avaliacao;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public AvaliacaoDTO toDto(Avaliacao entity) {
        if (entity == null) return null;
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setFilmeId(entity.getFilme().getId());
        dto.setNota(entity.getNota());
        dto.setComentario(entity.getComentario());
        dto.setNomeUsuario(entity.getUsuario().getNome());
        dto.setTituloFilme(entity.getFilme().getTitulo());
        return dto;
    }

    public Avaliacao toEntity(AvaliacaoDTO dto, Usuario usuario, Filme filme) {
        if (dto == null) return null;
        Avaliacao entity = new Avaliacao();
        entity.setId(dto.getId());
        entity.setUsuario(usuario);
        entity.setFilme(filme);
        entity.setNota(dto.getNota());
        entity.setComentario(dto.getComentario());
        return entity;
    }
}