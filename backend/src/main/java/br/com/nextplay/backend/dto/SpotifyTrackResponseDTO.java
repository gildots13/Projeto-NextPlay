package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyTrackResponseDTO {

    private List<SpotifyTrackDTO> items;

    public List<SpotifyTrackDTO> getItems() { return items; }
    public void setItems(List<SpotifyTrackDTO> items) { this.items = items; }
}