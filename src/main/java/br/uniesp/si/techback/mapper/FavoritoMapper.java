package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FavoritoDTO;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FavoritoMapper {

    public FavoritoDTO toDto(Favorito entity) {
        if (entity == null) {
            return null;
        }

        FavoritoDTO dto = new FavoritoDTO();
        dto.setId(entity.getId());

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
        }

        if (entity.getFilme() != null) {
            dto.setFilmeId(entity.getFilme().getId());
            dto.setTituloFilme(entity.getFilme().getTitulo());
        }

        return dto;
    }

    public Favorito toEntity(FavoritoDTO dto, Usuario usuario, Filme filme) {
        if (dto == null) {
            return null;
        }

        Favorito entity = new Favorito();
        entity.setId(dto.getId());
        entity.setUsuario(usuario);
        entity.setFilme(filme);

        return entity;
    }
}