package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario criar(Usuario usuario) {
        usuario.setCriadoEm(LocalDateTime.now());
        usuario.setAtualizadoEm(LocalDateTime.now());
        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario dadosAtualizados) {
        Usuario existente = buscarPorId(id);

        existente.setNomeCompleto(dadosAtualizados.getNomeCompleto());
        existente.setEmail(dadosAtualizados.getEmail());
        existente.setDataNascimento(dadosAtualizados.getDataNascimento());
        existente.setCpfCnpj(dadosAtualizados.getCpfCnpj());
        existente.setPerfil(dadosAtualizados.getPerfil());
        existente.setSenhaHash(dadosAtualizados.getSenhaHash());
        existente.setAtualizadoEm(LocalDateTime.now());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        Usuario existente = buscarPorId(id);
        repository.delete(existente);
    }
}