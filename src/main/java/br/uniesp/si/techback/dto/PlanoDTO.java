package br.uniesp.si.techback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDTO {

    private Long id;
    private String nome;
    private String codigo;
    private Double preco;
}