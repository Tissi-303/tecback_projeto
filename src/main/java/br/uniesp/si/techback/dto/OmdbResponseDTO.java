package br.uniesp.si.techback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OmdbResponseDTO {

    @JsonProperty("Title")
    private String titulo;

    @JsonProperty("Year")
    private String ano;

    @JsonProperty("Plot")
    private String sinopse;

    @JsonProperty("Poster")
    private String linkPoster;

    @JsonProperty("imdbRating")
    private String notaOmdb; //
}