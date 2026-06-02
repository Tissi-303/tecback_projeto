package br.uniesp.si.techback.dto;

import br.uniesp.si.techback.enums.TipoAssinatura;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssinaturaDTO {

    private Long id;

    @NotNull(message = "O tipo de assinatura (BASICO, PRO, PREMIUM) é obrigatório")
    private TipoAssinatura tipo;

    @NotNull(message = "O preço da assinatura é obrigatório")
    @Positive(message = "O preço da assinatura deve ser um valor maior que zero")
    private Double preco;

    private Boolean ativo = true; // Inicia ativa por padrão

    @NotNull(message = "O ID do usuário é obrigatório para vincular à assinatura")
    private Long usuarioId;
}