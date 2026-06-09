package br.uniesp.si.techback.model;

import java.io.Serializable;
import java.util.Objects;

public class FavoritoId implements Serializable {

    private Long usuario;
    private Long conteudo;

    public FavoritoId() {}

    public FavoritoId(Long usuario, Long conteudo) {
        this.usuario = usuario;
        this.conteudo = conteudo;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }

    public Long getConteudo() {
        return conteudo;
    }

    public void setConteudo(Long conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritoId that = (FavoritoId) o;
        return Objects.equals(usuario, that.usuario) && Objects.equals(conteudo, that.conteudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, conteudo);
    }
}