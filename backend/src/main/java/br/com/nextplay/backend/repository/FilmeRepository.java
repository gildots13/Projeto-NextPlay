package br.com.nextplay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nextplay.backend.model.Filme;
import java.util.List;
import java.util.Set;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    
    boolean existsByTmdbId(Long tmdbId);
    List<Filme> findTop10ByGenerosContainingIgnoreCaseAndIdNotIn(String genero, Set<Long> idsIgnorados);
}