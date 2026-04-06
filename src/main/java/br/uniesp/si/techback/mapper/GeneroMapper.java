package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.GeneroDTO;
import br.uniesp.si.techback.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroDTO toDto(Genero entity) {
        return new GeneroDTO(entity.getId(), entity.getNome());
    }

    public Genero toEntity(GeneroDTO dto) {
        Genero entity = new Genero();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }
}