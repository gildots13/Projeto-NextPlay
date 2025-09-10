package br.com.nextplay.backend.service;

import br.com.nextplay.backend.model.Avaliacao;
import br.com.nextplay.backend.model.Filme;
import br.com.nextplay.backend.repository.AvaliacaoRepository;
import br.com.nextplay.backend.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> gerarRecomendacoes(Long usuarioId) {
        List<Avaliacao> avaliacoesAltas = avaliacaoRepository.findByUsuarioIdAndNotaGreaterThanEqual(usuarioId, 4);

        if (avaliacoesAltas.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> generosPreferidos = avaliacoesAltas.stream()
            .map(avaliacao -> avaliacao.getFilme().getGeneros())
            .flatMap(generosStr -> Arrays.stream(generosStr.split(", ")))
            .collect(Collectors.toSet());

        List<Avaliacao> todasAvaliacoes = avaliacaoRepository.findByUsuarioId(usuarioId);
        Set<Long> idsFilmesAvaliados = todasAvaliacoes.stream()
            .map(avaliacao -> avaliacao.getFilme().getId())
            .collect(Collectors.toSet());
        Set<Filme> recomendacoes = new HashSet<>();
        for (String genero : generosPreferidos) {
            List<Filme> filmesPorGenero = filmeRepository.findTop10ByGenerosContainingIgnoreCaseAndIdNotIn(genero, idsFilmesAvaliados);
            recomendacoes.addAll(filmesPorGenero);
        }
        List<Filme> recomendacoesFinais = new ArrayList<>(recomendacoes);
        Collections.shuffle(recomendacoesFinais);

        return recomendacoesFinais;
    }
}