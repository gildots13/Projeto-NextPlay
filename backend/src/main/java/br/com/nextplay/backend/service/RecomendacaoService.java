package br.com.nextplay.backend.service;

import br.com.nextplay.backend.model.*;
import br.com.nextplay.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    @Autowired private AvaliacaoFilmeRepository avaliacaoFilmeRepository;
    @Autowired private FilmeRepository filmeRepository;
    @Autowired private AvaliacaoSerieRepository avaliacaoSerieRepository;
    @Autowired private SerieRepository serieRepository;

    public List<Filme> gerarRecomendacoesDeFilmes(Long usuarioId) {
        List<AvaliacaoFilme> avaliacoesAltas = avaliacaoFilmeRepository.findByUsuarioIdAndNotaGreaterThanEqual(usuarioId, 4);
        if (avaliacoesAltas.isEmpty()) return Collections.emptyList();
        Set<String> generosPreferidos = avaliacoesAltas.stream().map(a -> a.getFilme().getGeneros()).filter(Objects::nonNull).flatMap(g -> Arrays.stream(g.split(", "))).collect(Collectors.toSet());
        if (generosPreferidos.isEmpty()) return Collections.emptyList();
        Set<Long> idsFilmesAvaliados = avaliacaoFilmeRepository.findByUsuarioId(usuarioId).stream().map(a -> a.getFilme().getId()).collect(Collectors.toSet());
        Set<Filme> recomendacoes = new HashSet<>();
        for (String genero : generosPreferidos) {
            recomendacoes.addAll(filmeRepository.findTop10ByGenerosContainingIgnoreCaseAndIdNotIn(genero, idsFilmesAvaliados));
        }
        List<Filme> listaFinal = new ArrayList<>(recomendacoes);
        Collections.shuffle(listaFinal);
        return listaFinal.stream().limit(20).collect(Collectors.toList());
    }

    public List<Serie> gerarRecomendacoesDeSeries(Long usuarioId) {
        List<AvaliacaoSerie> avaliacoesAltas = avaliacaoSerieRepository.findByUsuarioIdAndNotaGreaterThanEqual(usuarioId, 4);
        if (avaliacoesAltas.isEmpty()) return Collections.emptyList();
        Set<String> generosPreferidos = avaliacoesAltas.stream().map(a -> a.getSerie().getGeneros()).filter(Objects::nonNull).flatMap(g -> Arrays.stream(g.split(", "))).collect(Collectors.toSet());
        if (generosPreferidos.isEmpty()) return Collections.emptyList();
        Set<Long> idsSeriesAvaliadas = avaliacaoSerieRepository.findByUsuarioId(usuarioId).stream().map(a -> a.getSerie().getId()).collect(Collectors.toSet());
        Set<Serie> recomendacoes = new HashSet<>();
        for (String genero : generosPreferidos) {
            recomendacoes.addAll(serieRepository.findTop10ByGenerosContainingIgnoreCaseAndIdNotIn(genero, idsSeriesAvaliadas));
        }
        List<Serie> listaFinal = new ArrayList<>(recomendacoes);
        Collections.shuffle(listaFinal);
        return listaFinal.stream().limit(20).collect(Collectors.toList());
    }
}