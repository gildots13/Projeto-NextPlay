package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.model.Filme;
import br.com.nextplay.backend.model.Serie;
import br.com.nextplay.backend.service.RecomendacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recomendacoes")
@CrossOrigin(origins = "*")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @GetMapping("/filmes/{usuarioId}")
    public ResponseEntity<List<Filme>> obterRecomendacoesFilmes(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(recomendacaoService.gerarRecomendacoesDeFilmes(usuarioId));
    }

    @GetMapping("/series/{usuarioId}")
    public ResponseEntity<List<Serie>> obterRecomendacoesSeries(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(recomendacaoService.gerarRecomendacoesDeSeries(usuarioId));
    }
}