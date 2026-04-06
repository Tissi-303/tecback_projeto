package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.DiretorDTO;
import br.uniesp.si.techback.model.Diretor;
import org.springframework.stereotype.Component;

@Component
public class DiretorMapper {

    public DiretorDTO toDto(Diretor entity) {
        if (entity == null) return null;
        DiretorDTO dto = new DiretorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setNacionalidade(entity.getNacionalidade());
        return dto;
    }

    public Diretor toEntity(DiretorDTO dto) {
        if (dto == null) return null;
        Diretor entity = new Diretor();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setNacionalidade(dto.getNacionalidade());
        return entity;
    }
}