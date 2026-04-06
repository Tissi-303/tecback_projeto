package br.uniesp.si.techback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoDTO {

    private Long id;
    private Long usuarioId;
    private Long filmeId;
    private String tituloFilme; // Informativo para o retorno
}