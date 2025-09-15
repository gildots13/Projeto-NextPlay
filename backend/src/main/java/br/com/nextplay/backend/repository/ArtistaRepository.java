package br.com.nextplay.backend.repository;
import br.com.nextplay.backend.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    boolean existsBySpotifyId(String spotifyId);
    List<Artista> findByGenerosContainingIgnoreCaseAndSpotifyIdNotIn(String genero, Set<String> idsIgnorados);
}