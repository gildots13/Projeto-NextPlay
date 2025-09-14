package br.com.nextplay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nextplay.backend.model.AvaliacaoSerie;

public interface AvaliacaoSerieRepository extends JpaRepository<AvaliacaoSerie, Long> {
}