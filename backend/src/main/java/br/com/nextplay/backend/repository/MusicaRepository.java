package br.com.nextplay.backend.repository;

import br.com.nextplay.backend.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    boolean existsBySpotifyId(String spotifyId);
    List<Musica> findByAlbumId(Long albumId);
}