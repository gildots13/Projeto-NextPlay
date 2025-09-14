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
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String spotifyId;

    @Column(nullable = false)
    private String titulo;

    private String anoLancamento;

    private String urlCapa;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSpotifyId() { return spotifyId; }
    public void setSpotifyId(String spotifyId) { this.spotifyId = spotifyId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(String anoLancamento) { this.anoLancamento = anoLancamento; }
    public String getUrlCapa() { return urlCapa; }
    public void setUrlCapa(String urlCapa) { this.urlCapa = urlCapa; }
    public Artista getArtista() { return artista; }
    public void setArtista(Artista artista) { this.artista = artista; }
}