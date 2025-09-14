package br.com.nextplay.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacoes_series")
public class AvaliacaoSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Serie getSerie() { return serie; }
    public void setSerie(Serie serie) { this.serie = serie; }
}