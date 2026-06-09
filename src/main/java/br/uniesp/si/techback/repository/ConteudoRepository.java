package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.enums.TipoConteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    @Query("SELECT c FROM Conteudo c WHERE " +
            "(:tipo IS NULL OR c.tipo = :tipo) AND " +
            "(:genero IS NULL OR LOWER(c.genero) LIKE LOWER(CONCAT('%', :genero, '%'))) AND " +
            "(:q IS NULL OR LOWER(c.titulo) LIKE LOWER(CONCAT('%', :q, '%')))")
    List<Conteudo> buscarComFiltros(@Param("tipo") TipoConteudo tipo,
                                    @Param("genero") String genero,
                                    @Param("q") String q);
}