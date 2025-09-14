package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtistDTO {
    private String id;
    private String name;
    private List<String> genres;
    private List<SpotifyImageDTO> images;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public List<SpotifyImageDTO> getImages() { return images; }
    public void setImages(List<SpotifyImageDTO> images) { this.images = images; }
}