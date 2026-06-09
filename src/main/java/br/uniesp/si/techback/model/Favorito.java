package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_favoritos")
@Data //Garante que o Lombok crie os métodos
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {

    @EmbeddedId
    private FavoritoId id;

    @ManyToOne
    @MapsId("usuario")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("conteudo")
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;

    private LocalDateTime adicionadoEm;
}