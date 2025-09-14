package br.com.nextplay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nextplay.backend.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    boolean existsByTmdbId(Long tmdbId);

}
