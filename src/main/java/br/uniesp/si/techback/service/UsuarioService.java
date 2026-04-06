package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.UsuarioDTO;
import br.uniesp.si.techback.mapper.UsuarioMapper;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listar() {
        log.info("Listando todos os usuários");
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return mapper.toDto(usuario);
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        log.info("Criando usuário: {}", dto.getEmail());

        Usuario entity = mapper.toEntity(dto);

        // Criptografia da senha antes do save
        if (dto.getSenha() != null) {
            entity.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        try {
            Usuario salvo = repository.save(entity);
            return mapper.toDto(salvo);
        } catch (Exception e) {
            log.error("Erro ao salvar usuário (provável email duplicado): {}", e.getMessage());
            throw new RuntimeException("Erro ao salvar: E-mail já cadastrado.");
        }
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        return mapper.toDto(repository.save(existente));
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}