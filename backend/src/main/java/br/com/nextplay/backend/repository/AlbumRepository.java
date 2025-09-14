package br.com.nextplay.backend.repository;

import br.com.nextplay.backend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsBySpotifyId(String spotifyId);
    List<Album> findByArtistaId(Long artistaId);
}
