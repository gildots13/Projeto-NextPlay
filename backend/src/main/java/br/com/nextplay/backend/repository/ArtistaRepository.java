package br.com.nextplay.backend.repository;

import br.com.nextplay.backend.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    boolean existsBySpotifyId(String spotifyId);

}