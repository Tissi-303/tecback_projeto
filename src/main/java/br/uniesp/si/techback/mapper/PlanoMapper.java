package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.PlanoDTO;
import br.uniesp.si.techback.model.Plano;
import org.springframework.stereotype.Component;

@Component
public class PlanoMapper {

    public PlanoDTO toDto(Plano entity) {
        if (entity == null) return null;
        PlanoDTO dto = new PlanoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCodigo(entity.getCodigo());
        dto.setPreco(entity.getPreco());
        return dto;
    }

    public Plano toEntity(PlanoDTO dto) {
        if (dto == null) return null;
        Plano entity = new Plano();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCodigo(dto.getCodigo());
        entity.setPreco(dto.getPreco());
        return entity;
    }
}