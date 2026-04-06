package br.uniesp.si.techback.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroDTO {

    private Long id;

    @NotBlank(message = "O nome do gênero é obrigatório")
    private String nome;
}