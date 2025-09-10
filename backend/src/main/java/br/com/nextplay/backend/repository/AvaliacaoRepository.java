package br.com.nextplay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nextplay.backend.model.Avaliacao;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByUsuarioIdAndNotaGreaterThanEqual(Long usuarioId, int nota);
    List<Avaliacao> findByUsuarioId(Long usuarioId);
}