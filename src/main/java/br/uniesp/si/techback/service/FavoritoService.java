package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConteudoService conteudoService;

    public List<Favorito> listarPorUsuario(Long usuarioId) {
        return repository.buscarFavoritosDoUsuario(usuarioId);
    }

    public Favorito adicionar(Long usuarioId, Long conteudoId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Conteudo conteudo = conteudoService.buscarPorId(conteudoId);

        Favorito favorito = new Favorito();
        favorito.setId(new FavoritoId(usuarioId, conteudoId));
        favorito.setUsuario(usuario);
        favorito.setConteudo(conteudo);
        favorito.setAdicionadoEm(LocalDateTime.now());

        return repository.save(favorito);
    }

    public void remover(Long usuarioId, Long conteudoId) {
        FavoritoId id = new FavoritoId(usuarioId, conteudoId);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Este conteúdo não está nos favoritos do usuário.");
        }
        repository.deleteById(id);
    }
}