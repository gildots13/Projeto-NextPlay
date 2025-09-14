package br.com.nextplay.backend.service;

import br.com.nextplay.backend.dto.*;
import br.com.nextplay.backend.model.Album;
import br.com.nextplay.backend.model.Artista;
import br.com.nextplay.backend.repository.AlbumRepository;
import br.com.nextplay.backend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.nextplay.backend.model.Musica;
import br.com.nextplay.backend.repository.MusicaRepository;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyService {

    @Value("${spotify.client.id}")
    private String clientId;
    @Value("${spotify.client.secret}")
    private String clientSecret;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private MusicaRepository musicaRepository;

    private String accessToken;
    private Instant tokenExpiration;

    private String getAccessToken() {
        if (accessToken == null || Instant.now().isAfter(tokenExpiration)) {
            System.out.println("Token do Spotify expirado ou nulo. Buscando um novo...");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            headers.set("Authorization", "Basic " + encodedAuth);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "client_credentials");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
            String authUrl = "https://accounts.spotify.com/api/token";
            
            ResponseEntity<SpotifyTokenDTO> response = restTemplate.postForEntity(authUrl, request, SpotifyTokenDTO.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                SpotifyTokenDTO tokenDto = response.getBody();
                this.accessToken = tokenDto.getAccessToken();
                this.tokenExpiration = Instant.now().plusSeconds(tokenDto.getExpiresIn() - 60);
                System.out.println("Novo token do Spotify obtido com sucesso!");
            } else {
                throw new RuntimeException("Não foi possível obter o token de acesso do Spotify.");
            }
        }
        return accessToken;
    }

    public void importarArtistas() {
        String token = getAccessToken();
        System.out.println("Iniciando importação de artistas do Spotify...");
        String url = "https://api.spotify.com/v1/search?q=year:2024&type=artist&limit=50";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<SpotifyArtistSearchResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpotifyArtistSearchResponseDTO.class);
        
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<SpotifyArtistDTO> artistasDaApi = response.getBody().getArtists().getItems();
            for (SpotifyArtistDTO artistaDto : artistasDaApi) {
                if (!artistaRepository.existsBySpotifyId(artistaDto.getId())) {
                    Artista artista = new Artista();
                    artista.setSpotifyId(artistaDto.getId());
                    artista.setNome(artistaDto.getName());
                    if (artistaDto.getGenres() != null && !artistaDto.getGenres().isEmpty()) {
                        artista.setGeneros(String.join(", ", artistaDto.getGenres()));
                    }
                    if (artistaDto.getImages() != null && !artistaDto.getImages().isEmpty()) {
                        artista.setUrlImagem(artistaDto.getImages().get(0).getUrl());
                    }
                    artistaRepository.save(artista);
                }
            }
        }
        System.out.println("Importação de artistas concluída!");
    }






    
    public void importarAlbunsDosArtistas() {
    String token = getAccessToken();
    System.out.println("Iniciando importação de álbuns dos artistas...");
    List<Artista> artistas = artistaRepository.findAll();

    for (Artista artista : artistas) {
        System.out.println("Buscando álbuns para o artista: " + artista.getNome());
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.spotify.com/v1")
                .path("/artists/{id}/albums")
                .queryParam("market", "BR")
                .queryParam("limit", 26)
                .buildAndExpand(artista.getSpotifyId())
                .toUriString();
        
        System.out.println("URL da API sendo chamada: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<SpotifyAlbumResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpotifyAlbumResponseDTO.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<SpotifyAlbumDTO> albunsDaApi = response.getBody().getItems();
                for (SpotifyAlbumDTO albumDto : albunsDaApi) {
                    if (!albumRepository.existsBySpotifyId(albumDto.getId())) {
                        Album album = new Album();
                        album.setSpotifyId(albumDto.getId());
                        album.setTitulo(albumDto.getName());
                        album.setAnoLancamento(albumDto.getReleaseDate());
                        if (albumDto.getImages() != null && !albumDto.getImages().isEmpty()) {
                            album.setUrlCapa(albumDto.getImages().get(0).getUrl());
                        }
                        album.setArtista(artista); 
                        albumRepository.save(album);
                    }
                }
            }
            Thread.sleep(700);
        } catch (Exception e) {
            System.err.println("Erro ao buscar álbuns para o artista " + artista.getNome() + ": " + e.getMessage());
        }
    }
    System.out.println("Importação de álbuns concluída!");
    }






    public void importarMusicasDosAlbuns() {
        String token = getAccessToken();
        System.out.println("Iniciando importação de músicas dos álbuns...");
        List<Album> albuns = albumRepository.findAll();
        long totalAlbuns = albumRepository.count();
        int contador = 0;
        for (Album album : albuns) {
            contador++;
            System.out.println("Processando álbum " + contador + "/" + totalAlbuns + ": " + album.getTitulo());
            String url = UriComponentsBuilder
                    .fromHttpUrl("https://api.spotify.com/v1")
                    .path("/albums/{id}/tracks")
                    .queryParam("market", "BR")
                    .buildAndExpand(album.getSpotifyId())
                    .toUriString();
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            try {
                ResponseEntity<SpotifyTrackResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpotifyTrackResponseDTO.class);
                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    List<SpotifyTrackDTO> musicasDaApi = response.getBody().getItems();
                    for (SpotifyTrackDTO musicaDto : musicasDaApi) {
                        if (musicaDto.getId() != null && !musicaRepository.existsBySpotifyId(musicaDto.getId())) {
                            Musica musica = new Musica();
                            musica.setSpotifyId(musicaDto.getId());
                            musica.setTitulo(musicaDto.getTitulo());
                            musica.setDuracaoEmMs(musicaDto.getDuracaoEmMs());
                            musica.setUrlPreview(musicaDto.getUrlPreview());
                            musica.setAlbum(album);
                            musicaRepository.save(musica);
                        }
                    }
                }
                Thread.sleep(700);
            } catch (Exception e) {
                System.err.println("Erro ao buscar músicas para o álbum '" + album.getTitulo() + "': " + e.getMessage());
            }
        }
        System.out.println("Importação de músicas concluída!");
    }
//VERSAO FUNCIONAL
}