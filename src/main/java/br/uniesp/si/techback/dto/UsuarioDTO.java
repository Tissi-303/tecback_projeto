package br.uniesp.si.techback.dto;

import br.uniesp.si.techback.validation.SenhaForte; // Import do validador que criamos
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF; // Import oficial para validar CPF

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @SenhaForte // 1. Garante que a senha digita atende aos requisitos fortes
    private String senha;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido ou com dígito verificador incorreto") // 2. Valida o tamanho e a matemática do CPF
    private String cpf;
}