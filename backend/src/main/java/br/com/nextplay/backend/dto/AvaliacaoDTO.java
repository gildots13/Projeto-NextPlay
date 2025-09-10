package br.com.nextplay.backend.dto;

public class AvaliacaoDTO {
    private Long usuarioId;
    private int nota;

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
}
