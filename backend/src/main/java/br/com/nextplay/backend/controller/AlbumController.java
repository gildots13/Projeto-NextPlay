package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.model.Musica;
import br.com.nextplay.backend.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/albuns")
@CrossOrigin(origins = "*")
public class AlbumController {

    @Autowired
    private MusicaRepository musicaRepository;

    @GetMapping("/{albumId}/musicas")
    public ResponseEntity<List<Musica>> listarMusicasPorAlbum(@PathVariable Long albumId) {
        List<Musica> musicas = musicaRepository.findByAlbumId(albumId);
        return ResponseEntity.ok(musicas);
    }
}