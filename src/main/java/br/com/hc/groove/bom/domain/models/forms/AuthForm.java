package br.com.hc.groove.bom.domain.models.forms;

import jakarta.validation.constraints.NotBlank;

public record AuthForm(@NotBlank String username, @NotBlank String password){
    
}
