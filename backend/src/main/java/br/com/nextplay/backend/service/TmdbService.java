package br.com.nextplay.backend.service;

import br.com.nextplay.backend.dto.GenreDTO;
import br.com.nextplay.backend.dto.GenreResponseDTO;
import br.com.nextplay.backend.dto.TmdbMovieResultDTO;
import br.com.nextplay.backend.dto.TmdbResponseDTO;
import br.com.nextplay.backend.model.Filme;
import br.com.nextplay.backend.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FilmeRepository filmeRepository;

    private final String TMDB_API_URL = "https://api.themoviedb.org/3";

    private Map<Integer, String> getGenreMap() {
        System.out.println("Buscando mapa de gêneros do TMDb...");
        String url = TMDB_API_URL + "/genre/movie/list?api_key=" + apiKey + "&language=pt-BR";
        GenreResponseDTO response = restTemplate.getForObject(url, GenreResponseDTO.class);
        
        if (response != null && response.getGenres() != null) {
            return response.getGenres().stream()
                    .collect(Collectors.toMap(GenreDTO::getId, GenreDTO::getName));
        }
        return java.util.Collections.emptyMap();
    }
    public void importarFilmesPopulares() {
        Map<Integer, String> genreMap = getGenreMap();

        System.out.println("Iniciando importação de filmes populares do TMDb...");
        for (int page = 1; page <= 10; page++) {
            System.out.println("Buscando dados da página: " + page);
            String url = TMDB_API_URL + "/movie/popular?api_key=" + apiKey + "&language=pt-BR&page=" + page;
            
            TmdbResponseDTO response = restTemplate.getForObject(url, TmdbResponseDTO.class);
            
            if (response != null && response.getResults() != null) {
                for (TmdbMovieResultDTO filmeDto : response.getResults()) {
                    if (!filmeRepository.existsByTmdbId(filmeDto.getId())) {
                        Filme filme = new Filme();
                        filme.setTmdbId(filmeDto.getId());
                        filme.setTitulo(filmeDto.getTitle());
                        filme.setDescricao(filmeDto.getOverview());
                        filme.setDataLancamento(filmeDto.getReleaseDate());
                        filme.setUrlPoster("https://image.tmdb.org/t/p/w500" + filmeDto.getPosterPath());

                        if (filmeDto.getGenreIds() != null) {
                            String generos = filmeDto.getGenreIds().stream()
                                    .map(genreId -> genreMap.getOrDefault(genreId, "Desconhecido"))
                                    .collect(Collectors.joining(", "));
                            filme.setGeneros(generos);
                        }
                        
                        filmeRepository.save(filme);
                    }
                }
                
                try {
                    Thread.sleep(700); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Importação de filmes concluída com sucesso!");
    }
}