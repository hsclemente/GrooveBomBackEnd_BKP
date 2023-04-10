package br.com.hc.groove.bom.models.dtos;

import br.com.hc.groove.bom.models.entities.Banda;
import jakarta.validation.constraints.NotBlank;

public record BandaDTO(Long id, @NotBlank(message = "A banda deve conter um nome") String nome, Double saldo) {
    public BandaDTO(Banda banda) {
        this(banda.getId(), banda.getNome(), banda.getSaldo());
    }
}
