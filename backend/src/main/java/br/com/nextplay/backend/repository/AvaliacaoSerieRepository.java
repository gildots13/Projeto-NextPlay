package br.com.nextplay.backend.repository;
import br.com.nextplay.backend.model.AvaliacaoSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AvaliacaoSerieRepository extends JpaRepository<AvaliacaoSerie, Long> {
    List<AvaliacaoSerie> findByUsuarioIdAndNotaGreaterThanEqual(Long usuarioId, int nota);
    List<AvaliacaoSerie> findByUsuarioId(Long usuarioId);
}