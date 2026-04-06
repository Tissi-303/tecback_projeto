package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.model.Diretor;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    public FilmeDTO toDto(Filme entity) {
        if (entity == null) return null;

        FilmeDTO dto = new FilmeDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setSinopse(entity.getSinopse());
        dto.setAnoLancamento(entity.getAnoLancamento());

        if (entity.getDiretor() != null) {
            dto.setDiretorId(entity.getDiretor().getId());
            dto.setNomeDiretor(entity.getDiretor().getNome());
        }

        if (entity.getGenero() != null) {
            dto.setGeneroId(entity.getGenero().getId());
        }

        return dto;
    }

    public Filme toEntity(FilmeDTO dto, Diretor diretor, Genero genero) {
        if (dto == null) return null;

        Filme entity = new Filme();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setSinopse(dto.getSinopse());
        entity.setAnoLancamento(dto.getAnoLancamento());
        entity.setDiretor(diretor);
        entity.setGenero(genero);

        return entity;
    }
}