package br.uniesp.si.techback.model;

import br.uniesp.si.techback.enums.Perfil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senhaHash;

    private LocalDate dataNascimento;

    private String cpfCnpj;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}