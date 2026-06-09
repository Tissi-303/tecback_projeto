package br.uniesp.si.techback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoDTO {

    private Long usuarioId;
    private Long filmeId;
    private String tituloFilme; // Informativo para o retorno
    private LocalDateTime adicionadoEm; // Adicionado para expor a data correta no Swagger
}