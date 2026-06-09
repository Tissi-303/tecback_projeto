package br.uniesp.si.techback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_planos")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;

    // Adicionado para consertar o erro do PlanoRepository (findByCodigo)
    @Column(nullable = false, unique = true)
    private String codigo;

    public Plano() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Getter e Setter do novo campo código exigido pelo repositório
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}