package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "metodo_pagamento")
public class MetodoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    // O CASCADE de deleção configurado via JPA/Banco
    private Usuario usuario;

    @Column(nullable = false, length = 20)
    private String bandeira;

    @Column(nullable = false, length = 4, columnDefinition = "CHAR(4)")
    private String ultimos4;

    @Column(name = "mes_exp", nullable = false)
    private Short mesExp;

    @Column(name = "ano_exp", nullable = false)
    private Short anoExp;

    @Column(name = "nome_portador", nullable = false, length = 150)
    private String nomePortador;

    @Column(name = "token_gateway", nullable = false, length = 120)
    private String tokenGateway;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getUltimos4() {
        return ultimos4;
    }

    public void setUltimos4(String ultimos4) {
        this.ultimos4 = ultimos4;
    }

    public Short getMesExp() {
        return mesExp;
    }

    public void setMesExp(Short mesExp) {
        this.mesExp = mesExp;
    }

    public Short getAnoExp() {
        return anoExp;
    }

    public void setAnoExp(Short anoExp) {
        this.anoExp = anoExp;
    }

    public String getNomePortador() {
        return nomePortador;
    }

    public void setNomePortador(String nomePortador) {
        this.nomePortador = nomePortador;
    }

    public String getTokenGateway() {
        return tokenGateway;
    }

    public void setTokenGateway(String tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}