package br.com.nextplay.backend.repository;
import br.com.nextplay.backend.model.AvaliacaoMusica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Set;
public interface AvaliacaoMusicaRepository extends JpaRepository<AvaliacaoMusica, Long> {
    List<AvaliacaoMusica> findByUsuarioIdAndNotaGreaterThanEqual(Long usuarioId, int nota);
    @Query("SELECT DISTINCT a.musica.album.id FROM AvaliacaoMusica a WHERE a.usuario.id = :usuarioId")
    Set<Long> findAlbumIdsAvaliadosPorUsuarioId(@Param("usuarioId") Long usuarioId);
}