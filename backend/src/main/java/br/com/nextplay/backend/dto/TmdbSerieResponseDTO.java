package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbSerieResponseDTO {
    
    private List<TmdbSerieResultDTO> results;

    public List<TmdbSerieResultDTO> getResults() { return results; }
    public void setResults(List<TmdbSerieResultDTO> results) { this.results = results; }
}