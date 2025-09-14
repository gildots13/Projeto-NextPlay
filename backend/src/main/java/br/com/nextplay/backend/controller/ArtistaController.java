package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.model.Album;
import br.com.nextplay.backend.model.Artista;
import br.com.nextplay.backend.repository.AlbumRepository;
import br.com.nextplay.backend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/artistas")
@CrossOrigin(origins = "*")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public ResponseEntity<List<Artista>> listarTodosOsArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/{artistaId}/albuns")
    public ResponseEntity<List<Album>> listarAlbunsPorArtista(@PathVariable Long artistaId) {
        List<Album> albuns = albumRepository.findByArtistaId(artistaId);
        return ResponseEntity.ok(albuns);
    }
}