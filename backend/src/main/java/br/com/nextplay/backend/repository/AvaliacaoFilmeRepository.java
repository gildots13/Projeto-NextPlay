package br.com.nextplay.backend.repository;

import br.com.nextplay.backend.model.AvaliacaoFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoFilmeRepository extends JpaRepository<AvaliacaoFilme, Long> {
    List<AvaliacaoFilme> findByUsuarioIdAndNotaGreaterThanEqual(Long usuarioId, int nota);
    List<AvaliacaoFilme> findByUsuarioId(Long usuarioId);
}