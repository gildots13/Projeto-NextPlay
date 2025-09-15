package br.com.nextplay.backend.repository;
import br.com.nextplay.backend.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;
public interface SerieRepository extends JpaRepository<Serie, Long> {
    boolean existsByTmdbId(Long tmdbId);
    List<Serie> findTop10ByGenerosContainingIgnoreCaseAndIdNotIn(String genero, Set<Long> idsIgnorados);
}