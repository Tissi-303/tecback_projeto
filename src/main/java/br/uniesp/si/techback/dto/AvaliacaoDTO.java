package br.uniesp.si.techback.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoDTO {

    private Long id;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "O ID do filme é obrigatório")
    private Long filmeId;

    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 10, message = "A nota máxima é 10")
    private Integer nota;

    @NotBlank(message = "O comentário não pode estar vazio")
    private String comentario;

    private String nomeUsuario; // Informativo
    private String tituloFilme; // Informativo
}