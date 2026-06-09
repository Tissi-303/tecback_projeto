package br.uniesp.si.techback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {

    private Long id;
    private String titulo;
    private String sinopse;

    // Campo usado pelos testes
    private LocalDate dataLancamento;

    // Mantido para compatibilidade com parte do projeto
    private Integer anoLancamento;

    // Informações sobre diretor/genero no DTO
    private Long diretorId;
    private String nomeDiretor;
    private Long generoId;

    // Campo de compatibilidade (String) esperado em alguns testes
    private String genero; // ex: "Ação"

    private Integer duracaoMinutos;
    private String classificacaoIndicativa;

    // Nota procedente do OMDB
    private String notaOmdb;
}
