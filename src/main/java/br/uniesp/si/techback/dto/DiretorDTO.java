package br.uniesp.si.techback.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiretorDTO {

    private Long id;

    @NotBlank(message = "O nome do diretor é obrigatório")
    private String nome;

    private String nacionalidade;
}