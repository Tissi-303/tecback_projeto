package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tb_diretores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Campo adicionado para corrigir o erro do Mapper!
    private String nacionalidade;

    @OneToMany(mappedBy = "diretorEntity", cascade = CascadeType.ALL)
    private List<Filme> filmes;
}