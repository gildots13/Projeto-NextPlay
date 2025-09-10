package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreResponseDTO {
    private List<GenreDTO> genres;

    public List<GenreDTO> getGenres() { return genres; }
    public void setGenres(List<GenreDTO> genres) { this.genres = genres; }
}