package br.uniesp.si.techback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssinaturaDTO {

    private Long id;
    private Long usuarioId;
    private Long planoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean ativo;
}