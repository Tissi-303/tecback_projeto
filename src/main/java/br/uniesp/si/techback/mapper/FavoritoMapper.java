package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FavoritoDTO;
import br.uniesp.si.techback.model.Favorito;
import org.springframework.stereotype.Component;

@Component
public class FavoritoMapper {

    public FavoritoDTO toDto(Favorito entity) {
        if (entity == null) return null;

        FavoritoDTO dto = new FavoritoDTO();

        if (entity.getUsuario() != null) {
            dto.setUsuarioId(entity.getUsuario().getId());
        }

        if (entity.getConteudo() != null) {
            dto.setFilmeId(entity.getConteudo().getId()); // Pega direto o Long do seu Conteudo!
        }

        return dto;
    }
}