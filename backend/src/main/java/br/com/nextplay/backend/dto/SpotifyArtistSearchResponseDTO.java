package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtistSearchResponseDTO {
    private ArtistsWrapper artists;
    public ArtistsWrapper getArtists() { return artists; }
    public void setArtists(ArtistsWrapper artists) { this.artists = artists; }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArtistsWrapper { 
        private List<SpotifyArtistDTO> items;
        public List<SpotifyArtistDTO> getItems() { return items; }
        public void setItems(List<SpotifyArtistDTO> items) { this.items = items; }
    }
}