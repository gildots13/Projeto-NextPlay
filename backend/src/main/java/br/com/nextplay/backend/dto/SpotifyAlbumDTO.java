package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAlbumDTO {

    private String id;
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;

    private List<SpotifyImageDTO> images;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public List<SpotifyImageDTO> getImages() { return images; }
    public void setImages(List<SpotifyImageDTO> images) { this.images = images; }
}