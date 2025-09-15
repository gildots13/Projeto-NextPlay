package br.com.nextplay.backend.repository;
import br.com.nextplay.backend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;
public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsBySpotifyId(String spotifyId);
    List<Album> findByArtistaId(Long artistaId);
    List<Album> findTop15ByIdNotIn(Set<Long> idsIgnorados);
}