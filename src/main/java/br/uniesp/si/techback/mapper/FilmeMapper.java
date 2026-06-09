package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.FilmeDTO;
import br.uniesp.si.techback.model.Diretor;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    // Método existente (mantido) - nome antigo toDto (minúsculo d)
    public FilmeDTO toDto(Filme entity) {
        if (entity == null) return null;

        FilmeDTO dto = new FilmeDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setSinopse(entity.getSinopse());
        dto.setAnoLancamento(entity.getAnoLancamento());

        // Nota OMDB
        dto.setNotaOmdb(entity.getNotaOmdb());

        // Se houver Diretor/Genero como entidade, preenche os ids/nome
        if (entity.getDiretorEntity() != null) {
            dto.setDiretorId(entity.getDiretorEntity().getId());
            dto.setNomeDiretor(entity.getDiretorEntity().getNome());
        } else if (entity.getNomeDiretor() != null) {
            dto.setNomeDiretor(entity.getNomeDiretor());
        }

        if (entity.getGeneroEntity() != null) {
            dto.setGeneroId(entity.getGeneroEntity().getId());
        } else if (entity.getGenero() != null) {
            // se não houver entidade mas houver nome de genero (compatibilidade)
            dto.setGenero(entity.getGenero());
        }

        return dto;
    }

    // Versão compatível com os testes (assina toDTO com D maiúsculo e mapeia data/genero string)
    public FilmeDTO toDTO(Filme entity) {
        if (entity == null) return null;

        FilmeDTO dto = new FilmeDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setSinopse(entity.getSinopse());
        dto.setDataLancamento(entity.getDataLancamento());
        dto.setAnoLancamento(entity.getAnoLancamento());
        dto.setDuracaoMinutos(entity.getDuracaoMinutos());
        dto.setClassificacaoIndicativa(entity.getClassificacaoIndicativa());
        dto.setNotaOmdb(entity.getNotaOmdb());

        // Preenche genero como string (preferência para campo transiente 'genero',
        // senão tenta extrair do generoEntity)
        if (entity.getGenero() != null) {
            dto.setGenero(entity.getGenero());
        } else if (entity.getGeneroEntity() != null) {
            dto.setGenero(entity.getGeneroEntity().getNome());
            dto.setGeneroId(entity.getGeneroEntity().getId());
        }

        // Diretor: nome direto no DTO ou via entidade
        if (entity.getNomeDiretor() != null) {
            dto.setNomeDiretor(entity.getNomeDiretor());
        } else if (entity.getDiretorEntity() != null) {
            dto.setNomeDiretor(entity.getDiretorEntity().getNome());
            dto.setDiretorId(entity.getDiretorEntity().getId());
        }

        return dto;
    }

    // Método existente (mantido) — converte DTO para entidade usando Diretor e Genero (entidades)
    public Filme toEntity(FilmeDTO dto, Diretor diretor, Genero genero) {
        if (dto == null) return null;

        Filme entity = new Filme();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setSinopse(dto.getSinopse());
        entity.setAnoLancamento(dto.getAnoLancamento());
        entity.setDataLancamento(dto.getDataLancamento());
        entity.setDuracaoMinutos(dto.getDuracaoMinutos());
        entity.setClassificacaoIndicativa(dto.getClassificacaoIndicativa());
        entity.setNotaOmdb(dto.getNotaOmdb());

        entity.setDiretorEntity(diretor);
        entity.setGeneroEntity(genero);

        // se DTO tiver genero como string, salva também no campo transiente (compatibilidade)
        entity.setGenero(dto.getGenero());
        entity.setNomeDiretor(dto.getNomeDiretor());

        return entity;
    }

    // Versão compatível com os testes: toEntity que recebe apenas o DTO e preenche campos simples.
    public Filme toEntity(FilmeDTO dto) {
        if (dto == null) return null;

        Filme entity = new Filme();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setSinopse(dto.getSinopse());
        entity.setDataLancamento(dto.getDataLancamento());
        entity.setAnoLancamento(dto.getAnoLancamento());
        entity.setDuracaoMinutos(dto.getDuracaoMinutos());
        entity.setClassificacaoIndicativa(dto.getClassificacaoIndicativa());
        entity.setNotaOmdb(dto.getNotaOmdb());

        // Campo genero no DTO (string) -> armazena no campo transiente 'genero'
        entity.setGenero(dto.getGenero());
        entity.setNomeDiretor(dto.getNomeDiretor());

        return entity;
    }
}
