package br.com.nextplay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nextplay.backend.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}