package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.dto.AvaliacaoDTO;
import br.com.nextplay.backend.model.Avaliacao;
import br.com.nextplay.backend.model.Filme;
import br.com.nextplay.backend.model.Usuario;
import br.com.nextplay.backend.repository.AvaliacaoRepository;
import br.com.nextplay.backend.repository.FilmeRepository;
import br.com.nextplay.backend.repository.UsuarioRepository;
import br.com.nextplay.backend.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {
    
    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping("/importar")
    public ResponseEntity<String> importarFilmesPopulares() {
        tmdbService.importarFilmesPopulares();
        return ResponseEntity.ok("Processo de importação de filmes populares iniciado com sucesso!");
    }
    @GetMapping
    public ResponseEntity<List<Filme>> listarTodosOsFilmes() {
        List<Filme> filmes = filmeRepository.findAll();
        return ResponseEntity.ok(filmes);
    }


    @PostMapping("/{filmeId}/avaliar")
    public ResponseEntity<String> avaliarFilme(
            @PathVariable Long filmeId,
            @RequestBody AvaliacaoDTO avaliacaoDto) {
        
        Optional<Filme> filmeOpt = filmeRepository.findById(filmeId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(avaliacaoDto.getUsuarioId());

        if (filmeOpt.isEmpty() || usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setFilme(filmeOpt.get());
        novaAvaliacao.setUsuario(usuarioOpt.get());
        novaAvaliacao.setNota(avaliacaoDto.getNota());
        
        avaliacaoRepository.save(novaAvaliacao);

        return ResponseEntity.ok("Avaliação salva com sucesso!");
    }
}