package br.com.nextplay.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String spotifyId;

    @Column(nullable = false)
    private String titulo;

    private int duracaoEmMs;

    private String urlPreview;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSpotifyId() { return spotifyId; }
    public void setSpotifyId(String spotifyId) { this.spotifyId = spotifyId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracaoEmMs() { return duracaoEmMs; }
    public void setDuracaoEmMs(int duracaoEmMs) { this.duracaoEmMs = duracaoEmMs; }
    public String getUrlPreview() { return urlPreview; }
    public void setUrlPreview(String urlPreview) { this.urlPreview = urlPreview; }
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }
}