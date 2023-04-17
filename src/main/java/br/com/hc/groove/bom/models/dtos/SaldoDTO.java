package br.com.hc.groove.bom.models.dtos;

import jakarta.validation.constraints.DecimalMin;

public record SaldoDTO(@DecimalMin(value = "0.0", inclusive = true)Double valor) {}
