package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_diretor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private String nacionalidade;

    @OneToMany(mappedBy = "diretor", cascade = CascadeType.ALL)
    private List<Filme> filmes;
}