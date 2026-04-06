package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByFilmeId(Long filmeId);
    List<Avaliacao> findByUsuarioId(Long usuarioId);
}