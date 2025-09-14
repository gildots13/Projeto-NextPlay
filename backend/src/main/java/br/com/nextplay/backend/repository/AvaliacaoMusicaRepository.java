package br.com.nextplay.backend.repository;

import br.com.nextplay.backend.model.AvaliacaoMusica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoMusicaRepository extends JpaRepository<AvaliacaoMusica, Long> {
}