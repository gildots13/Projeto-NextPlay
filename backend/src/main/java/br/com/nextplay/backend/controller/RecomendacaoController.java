package br.com.nextplay.backend.controller;

import br.com.nextplay.backend.model.Filme;
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

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Filme>> obterRecomendacoes(@PathVariable Long usuarioId) {
        List<Filme> recomendacoes = recomendacaoService.gerarRecomendacoes(usuarioId);
        return ResponseEntity.ok(recomendacoes);
    }
}