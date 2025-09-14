package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.dto.AvaliacaoDTO;
import br.com.nextplay.backend.model.AvaliacaoMusica;
import br.com.nextplay.backend.model.Musica;
import br.com.nextplay.backend.model.Usuario;
import br.com.nextplay.backend.repository.AvaliacaoMusicaRepository;
import br.com.nextplay.backend.repository.MusicaRepository;
import br.com.nextplay.backend.repository.UsuarioRepository;
import br.com.nextplay.backend.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/musica")
@CrossOrigin(origins = "*")
public class MusicaController {
    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoMusicaRepository avaliacaoMusicaRepository;

    @PostMapping("/importar-artistas")
    public ResponseEntity<String> importarArtistas() {
        spotifyService.importarArtistas();
        return ResponseEntity.ok("Importação de artistas iniciada com sucesso!");
    }

    @PostMapping("/importar-albuns")
    public ResponseEntity<String> importarAlbuns() {
        spotifyService.importarAlbunsDosArtistas();
        return ResponseEntity.ok("Importação de álbuns iniciada com sucesso!");
    }

    @PostMapping("/importar-musicas")
    public ResponseEntity<String> importarMusicas() {
        spotifyService.importarMusicasDosAlbuns();
        return ResponseEntity.ok("Importação de músicas iniciada com sucesso!");
    }
    
    @GetMapping
    public ResponseEntity<List<Musica>> listarTodasAsMusicas() {
        List<Musica> musicas = musicaRepository.findAll();
        return ResponseEntity.ok(musicas);
    }

    @PostMapping("/{musicaId}/avaliar")
    public ResponseEntity<String> avaliarMusica(
            @PathVariable Long musicaId,
            @RequestBody AvaliacaoDTO avaliacaoDto) {
        Optional<Musica> musicaOpt = musicaRepository.findById(musicaId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(avaliacaoDto.getUsuarioId());

        if (musicaOpt.isEmpty() || usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AvaliacaoMusica novaAvaliacao = new AvaliacaoMusica();
        novaAvaliacao.setMusica(musicaOpt.get());
        novaAvaliacao.setUsuario(usuarioOpt.get());
        novaAvaliacao.setNota(avaliacaoDto.getNota());
        
        avaliacaoMusicaRepository.save(novaAvaliacao);

        return ResponseEntity.ok("Avaliação de música salva com sucesso!");
    }
}