package br.com.nextplay.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long tmdbId;

    @Column(nullable = false)
    private String nome;

    @Column(length = 2000)
    private String descricao;

    private String urlPoster;

    private String dataPrimeiroEpisodio;

    private String generos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTmdbId() { return tmdbId; }
    public void setTmdbId(Long tmdbId) { this.tmdbId = tmdbId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getUrlPoster() { return urlPoster; }
    public void setUrlPoster(String urlPoster) { this.urlPoster = urlPoster; }
    public String getDataPrimeiroEpisodio() { return dataPrimeiroEpisodio; }
    public void setDataPrimeiroEpisodio(String dataPrimeiroEpisodio) { this.dataPrimeiroEpisodio = dataPrimeiroEpisodio; }
    public String getGeneros() { return generos; }
    public void setGeneros(String generos) { this.generos = generos; }
}