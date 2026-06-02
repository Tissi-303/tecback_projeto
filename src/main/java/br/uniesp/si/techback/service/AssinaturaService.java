package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.AssinaturaDTO;
import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.AssinaturaRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository; // Para buscar o dono da assinatura

    public List<AssinaturaDTO> listarTodos() {
        return assinaturaRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public AssinaturaDTO buscarPorId(Long id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada!"));
        return convertEntityToDto(assinatura);
    }

    public AssinaturaDTO salvar(AssinaturaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário informado não existe!"));

        Assinatura assinatura = new Assinatura();
        assinatura.setTipo(dto.getTipo());
        assinatura.setPreco(dto.getPreco());
        assinatura.setAtivo(dto.getAtivo());
        assinatura.setUsuario(usuario);

        Assinatura salva = assinaturaRepository.save(assinatura);
        return convertEntityToDto(salva);
    }

    public AssinaturaDTO atualizar(Long id, AssinaturaDTO dto) {
        Assinatura assinaturaExistente = assinaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada!"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário informado não existe!"));

        assinaturaExistente.setTipo(dto.getTipo());
        assinaturaExistente.setPreco(dto.getPreco());
        assinaturaExistente.setAtivo(dto.getAtivo());        assinaturaExistente.setUsuario(usuario);

        Assinatura atualizada = assinaturaRepository.save(assinaturaExistente);
        return convertEntityToDto(atualizada);
    }

    public void deletar(Long id) {
        if (!assinaturaRepository.existsById(id)) {
            throw new RuntimeException("Assinatura não existe!");
        }
        assinaturaRepository.deleteById(id);
    }

    // Métodos Auxiliares de Conversão (Mapper manual)
    private AssinaturaDTO convertEntityToDto(Assinatura entity) {
        return new AssinaturaDTO(
                entity.getId(),
                entity.getTipo(),
                entity.getPreco(),
                entity.getAtivo(),
                entity.getUsuario().getId()
        );
    }
}