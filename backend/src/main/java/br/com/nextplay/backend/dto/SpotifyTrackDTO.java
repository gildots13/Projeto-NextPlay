package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyTrackDTO {

    private String id;

    @JsonProperty("name")
    private String titulo;

    @JsonProperty("duration_ms")
    private int duracaoEmMs;

    @JsonProperty("preview_url")
    private String urlPreview;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracaoEmMs() { return duracaoEmMs; }
    public void setDuracaoEmMs(int duracaoEmMs) { this.duracaoEmMs = duracaoEmMs; }
    public String getUrlPreview() { return urlPreview; }
    public void setUrlPreview(String urlPreview) { this.urlPreview = urlPreview; }
}