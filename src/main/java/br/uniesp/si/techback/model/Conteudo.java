package br.uniesp.si.techback.model;

import br.uniesp.si.techback.enums.TipoConteudo;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_conteudos")
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoConteudo tipo;

    private Integer ano;
    private Integer duracaoMinutos;
    private Integer relevancia;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    private String trailerUrl;
    private String genero;
    private LocalDateTime criadoEm;
    private LocalDateTime updatedEm;

    public Conteudo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoConteudo getTipo() {
        return tipo;
    }

    public void setTipo(TipoConteudo tipo) {
        this.tipo = tipo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Integer getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(Integer relevancia) {
        this.relevancia = relevancia;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getUpdatedEm() {
        return updatedEm;
    }

    public void setUpdatedEm(LocalDateTime updatedEm) {
        this.updatedEm = updatedEm;
    }
}