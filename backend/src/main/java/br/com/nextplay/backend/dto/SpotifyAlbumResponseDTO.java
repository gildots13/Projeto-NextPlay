package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAlbumResponseDTO {

    private List<SpotifyAlbumDTO> items;

    public List<SpotifyAlbumDTO> getItems() { return items; }
    public void setItems(List<SpotifyAlbumDTO> items) { this.items = items; }
}
