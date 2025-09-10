package br.com.nextplay.backend.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.nextplay.backend.dto.TmdbMovieResultDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbResponseDTO {

    private List<TmdbMovieResultDTO> results;

    public List<TmdbMovieResultDTO> getResults() {
        return results;
    }

    public void setResults(List<TmdbMovieResultDTO> results) {
        this.results = results;
    }
}