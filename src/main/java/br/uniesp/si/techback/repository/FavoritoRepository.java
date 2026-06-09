package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

    @Query("SELECT f FROM Favorito f WHERE f.id.usuario = :usuarioId ORDER BY f.adicionadoEm DESC")
    List<Favorito> buscarFavoritosDoUsuario(@Param("usuarioId") Long usuarioId);
}