package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.dto.TmdbMovieResultDTO;
import br.com.nextplay.backend.model.Filme;
import br.com.nextplay.backend.repository.FilmeRepository;
import br.com.nextplay.backend.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {

    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private FilmeRepository filmeRepository;

    @PostMapping("/importar")
    public ResponseEntity<String> importarFilmesPopulares() {
        List<TmdbMovieResultDTO> filmesParaImportar = tmdbService.buscarFilmesPopulares();

        for (TmdbMovieResultDTO filmeDto : filmesParaImportar) {
            Filme filme = new Filme();
            filme.setTmdbId(filmeDto.getId());
            filme.setTitulo(filmeDto.getTitle());
            filme.setDescricao(filmeDto.getOverview());
            filme.setDataLancamento(filmeDto.getReleaseDate());
            filme.setUrlPoster("https://image.tmdb.org/t/p/w500" + filmeDto.getPosterPath());
            filmeRepository.save(filme);
        }

        return ResponseEntity.ok(filmesParaImportar.size() + " filmes importados com sucesso!");
    }
    @GetMapping
    public ResponseEntity<List<Filme>> listarTodosOsFilmes() {
        List<Filme> filmes = filmeRepository.findAll();
        return ResponseEntity.ok(filmes);
    }
}