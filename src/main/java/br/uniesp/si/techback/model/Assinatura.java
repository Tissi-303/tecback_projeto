package br.uniesp.si.techback.model;

import br.uniesp.si.techback.enums.TipoAssinatura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ASSINATURA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAssinatura tipo;

    @Column(nullable = false)
    private Double preco;

    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}