package br.uniesp.si.techback.mapper;

import br.uniesp.si.techback.dto.UsuarioDTO;
import br.uniesp.si.techback.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNomeCompleto(entity.getNomeCompleto());
        dto.setEmail(entity.getEmail());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setCpfCnpj(entity.getCpfCnpj());
        dto.setPerfil(entity.getPerfil());
        dto.setSenha(null);

        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setNomeCompleto(dto.getNomeCompleto());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setCpfCnpj(dto.getCpfCnpj());
        entity.setPerfil(dto.getPerfil());
        entity.setSenhaHash(dto.getSenha());

        return entity;
    }
}