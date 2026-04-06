package br.uniesp.si.techback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {

    private Long id;
    private String titulo;
    private String sinopse;
    private Integer anoLancamento;
    private Long diretorId;
    private String nomeDiretor; // Útil para exibir na listagem
    private Long generoId;
}