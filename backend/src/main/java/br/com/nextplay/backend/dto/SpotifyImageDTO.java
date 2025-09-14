package br.com.nextplay.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyImageDTO {
    private String url;
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}