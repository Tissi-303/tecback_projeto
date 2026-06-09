package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.AssinaturaDTO;
import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.AssinaturaRepository;
import br.uniesp.si.techback.repository.PlanoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanoRepository planoRepository;

    public List<AssinaturaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AssinaturaDTO buscarPorId(Long id) {
        Assinatura assinatura = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada."));
        return convertToDTO(assinatura);
    }

    public AssinaturaDTO criar(AssinaturaDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Plano plano = planoRepository.findById(dto.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado."));

        Assinatura assinatura = new Assinatura();
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);
        assinatura.setDataInicio(dto.getDataInicio());
        assinatura.setDataFim(dto.getDataFim());
        assinatura.setAtivo(true);

        Assinatura salva = repository.save(assinatura);
        return convertToDTO(salva);
    }

    public AssinaturaDTO atualizar(Long id, AssinaturaDTO dto) {
        Assinatura existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada."));

        Plano plano = planoRepository.findById(dto.getPlanoId())
                .orElseThrow(() -> new RuntimeException("Plano não encontrado."));

        existente.setPlano(plano);
        existente.setDataFim(dto.getDataFim());
        existente.setAtivo(dto.getAtivo());

        Assinatura atualizada = repository.save(existente);
        return convertToDTO(atualizada);
    }

    public void deletar(Long id) {
        Assinatura existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada."));
        repository.delete(existente);
    }

    private AssinaturaDTO convertToDTO(Assinatura assinatura) {
        AssinaturaDTO dto = new AssinaturaDTO();
        dto.setId(assinatura.getId());
        dto.setUsuarioId(assinatura.getUsuario().getId());
        dto.setPlanoId(assinatura.getPlano().getId());
        dto.setDataInicio(assinatura.getDataInicio());
        dto.setDataFim(assinatura.getDataFim());
        dto.setAtivo(assinatura.getAtivo());
        return dto;
    }
}