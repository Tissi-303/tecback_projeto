package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.enums.TipoConteudo;
import br.uniesp.si.techback.repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConteudoService {

    @Autowired
    private ConteudoRepository repository;

    public List<Conteudo> listar(TipoConteudo tipo, String genero, String q) {
        return repository.buscarComFiltros(tipo, genero, q);
    }

    public Conteudo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado com o ID informado."));
    }

    public Conteudo criar(Conteudo conteudo) {
        conteudo.setCriadoEm(LocalDateTime.now());
        conteudo.setUpdatedEm(LocalDateTime.now());
        return repository.save(conteudo);
    }

    public Conteudo atualizar(Long id, Conteudo dadosAtualizados) {
        Conteudo existente = buscarPorId(id);

        existente.setTitulo(dadosAtualizados.getTitulo());
        existente.setTipo(dadosAtualizados.getTipo());
        existente.setAno(dadosAtualizados.getAno());
        existente.setDuracaoMinutos(dadosAtualizados.getDuracaoMinutos());
        existente.setRelevancia(dadosAtualizados.getRelevancia());
        existente.setSinopse(dadosAtualizados.getSinopse());
        existente.setTrailerUrl(dadosAtualizados.getTrailerUrl());
        existente.setGenero(dadosAtualizados.getGenero());
        existente.setUpdatedEm(LocalDateTime.now());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        Conteudo existente = buscarPorId(id);
        repository.delete(existente);
    }
}