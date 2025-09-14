package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.dto.AvaliacaoDTO;
import br.com.nextplay.backend.model.AvaliacaoSerie;
import br.com.nextplay.backend.model.Usuario;
import br.com.nextplay.backend.repository.AvaliacaoSerieRepository;
import br.com.nextplay.backend.repository.UsuarioRepository;
import java.util.Optional;
import br.com.nextplay.backend.model.Serie;
import br.com.nextplay.backend.repository.SerieRepository;
import br.com.nextplay.backend.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/series")
@CrossOrigin(origins = "*")
public class SerieController {

    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoSerieRepository avaliacaoSerieRepository;

    @PostMapping("/importar")
    public ResponseEntity<String> importarSeriesPopulares() {
        tmdbService.importarSeriesPopulares();
        return ResponseEntity.ok("Processo de importação de séries populares iniciado com sucesso!");
    }
    @GetMapping
    public ResponseEntity<List<Serie>> listarTodasAsSeries() {
        List<Serie> series = serieRepository.findAll();
        return ResponseEntity.ok(series);
    }
    @PostMapping("/{serieId}/avaliar")
    public ResponseEntity<String> avaliarSerie(
            @PathVariable Long serieId,
            @RequestBody AvaliacaoDTO avaliacaoDto) {

        Optional<Serie> serieOpt = serieRepository.findById(serieId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(avaliacaoDto.getUsuarioId());

        if (serieOpt.isEmpty() || usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AvaliacaoSerie novaAvaliacao = new AvaliacaoSerie();
        novaAvaliacao.setSerie(serieOpt.get());
        novaAvaliacao.setUsuario(usuarioOpt.get());
        novaAvaliacao.setNota(avaliacaoDto.getNota());
        
        avaliacaoSerieRepository.save(novaAvaliacao);

        return ResponseEntity.ok("Avaliação de série salva com sucesso!");
    }
}