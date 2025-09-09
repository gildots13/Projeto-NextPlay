package br.com.nextplay.backend.service;

import br.com.nextplay.backend.dto.TmdbMovieResultDTO;
import br.com.nextplay.backend.dto.TmdbResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate;

    private final String TMDB_API_URL = "https://api.themoviedb.org/3";

    public List<TmdbMovieResultDTO> buscarFilmesPopulares() {
        String url = TMDB_API_URL + "/movie/popular?api_key=" + apiKey + "&language=pt-BR";
        
        TmdbResponseDTO response = restTemplate.getForObject(url, TmdbResponseDTO.class);
        
        if (response != null && response.getResults() != null) {
            return response.getResults();
        }
        return Collections.emptyList();
    }
}
