package br.com.nextplay.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacoes_musicas")
public class AvaliacaoMusica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "musica_id")
    private Musica musica;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Musica getMusica() { return musica; }
    public void setMusica(Musica musica) { this.musica = musica; }
}
